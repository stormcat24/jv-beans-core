/*
 * Copyright 2009-2011 the Stormcat Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.stormcat.jvbeans.jvlink.analyze;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stormcat.jvbeans.annotation.JvBean;
import org.stormcat.jvbeans.common.reflect.ClassUtil;
import org.stormcat.jvbeans.common.reflect.PackageUtil;
import org.stormcat.jvbeans.common.resource.PathUtil;
import org.stormcat.jvbeans.common.resource.ResourceUtil;
import org.stormcat.jvbeans.config.RecordTypeId;
import org.stormcat.jvbeans.exception.JvBeansRuntimeException;


/**
 * JV-DataをDTOにバインディングするための情報を保持したコンテナクラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvBeansContainer {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(JvBeansContainer.class);

    /** レコード種別IDとバインディング情報を保持する{@link Map} */
    private Map<String, JvBeanMeta> metaMap;

    private static final String RECORD_TYPE_ID_CLASS = "org.stormcat.jvbeans.jvlink.definitions.ConcreteRecordTypeId";

    private Class<? extends RecordTypeId> recordTypeIdClass;

    /**
     *
     * コンストラクタ
     */
    @SuppressWarnings("unchecked")
    public JvBeansContainer() {
        metaMap = new ConcurrentHashMap<String, JvBeanMeta>();
        recordTypeIdClass = (Class<? extends RecordTypeId>) ClassUtil.forName(RECORD_TYPE_ID_CLASS);
    }

    /**
     * 指定したパッケージ配下に属するクラスから、JV-Dataのバインディング情報を取得してコンテナに保持させます。
     * <p>クラスパスに通っていれば、ファイルシステム・Jarファイルどちらでもかまいません（Jarファイルが優先されます）。
     * @param dtoPackage 走査対象パッケージ
     */
    public void init(String dtoPackage) {
        logger.debug("JVBeansContainerを初期化します・・・。");
        URL url = Thread.currentThread().getContextClassLoader().getResource(ResourceUtil.getPath(ClassUtil.forName(RECORD_TYPE_ID_CLASS)));

        logger.debug(String.format("コンポーネント走査対象はパッケージ %s に所属するクラスです。", dtoPackage));
        List<Class<?>> classList = null;
        if ("jar".equals(url.getProtocol())) {
            logger.debug("クラスパスに通っているJarファイルからコンポーネント走査を行います。");
            try {
                JarFile jarFile = new JarFile(PathUtil.getJarFilePath(url));
                classList = PackageUtil.getClassList(jarFile, dtoPackage);
            } catch (IOException e) {
                throw new JvBeansRuntimeException(e);
            }
        } else {
            logger.debug("クラスパスに通っているファイルシステムからコンポーネント走査を行います。");
            classList = PackageUtil.getClassList(dtoPackage);
        }

        logger.debug("クラス走査を完了しました。メタ情報を組み立てます・・・");
        JvRecordMetaFactory metaFactory = new JvRecordMetaFactory(getRecordTypeIdClass());
        for (Class<?> c : classList) {
            JvBean jvBean = c.getAnnotation(JvBean.class);
            if (jvBean != null) {
                List<JvRecordMeta> metaItems = new ArrayList<JvRecordMeta>();
                for (Field field : c.getDeclaredFields()) {
                    metaItems.add(metaFactory.create(field));
                }
                metaMap.put(jvBean.recordTypeId(), new JvBeanMeta(c, metaItems));
                logger.debug(String.format("%s のバインディング情報を取得しました。", c.getName()));
            }
        }
    }

    /**
     * 指定のレコード種別IDのバインディング対象になるDTOの{@link Class}を返します。
     * @param recordTypeId レコード種別ID
     * @return バインディング対象クラス
     */
    public Class<?> getMappedType(RecordTypeId recordTypeId) {
        if (recordTypeId == null) {
            throw new IllegalArgumentException("レコード種別IDがnullになっています。");
        }
        return metaMap.get(recordTypeId.getValue()).getType();
    }

    /**
     * 指定のレコード種別IDのバインディング対象になるDTOのレコード長を返します。
     * @param recordTypeId レコード種別ID
     * @return DTOレコード長
     */
    public int getRecordByteLength(RecordTypeId recordTypeId) {
        if (recordTypeId == null) {
            throw new IllegalArgumentException("レコード種別IDがnullになっています。");
        }
        return metaMap.get(recordTypeId.getValue()).getByteLength();
    }

    /**
     * 指定のレコード種別IDのバインディング対象になるDTOの各レコードメタ情報を返します。
     * @param recordTypeId レコード種別ID
     * @return 各レコードメタ情報
     */
    public List<JvRecordMeta> getRecordMetaItems(String recordTypeId) {
        return metaMap.get(recordTypeId).getRecordMetaItems();
    }

    /**
     * {@link RecordTypeId}の実装Enum取得します。
     * @return recordTypeIdClass 実装Enum
     */
    public Class<? extends RecordTypeId> getRecordTypeIdClass() {
        return recordTypeIdClass;
    }

}
