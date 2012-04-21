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
package org.stormcat.jvbeans.jvlink;

import org.stormcat.jvbeans.jvlink.analyze.JvBeansContainer;
import org.stormcat.jvbeans.jvlink.analyze.JvBindingDtoFactory;


/**
 * DIコンテナのようなコンポーネントを管理するような環境ではない場合に、
 * JV-Beansを使用するうえで最低限必要なコンポーネントを簡易的に取得できるようにするクラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvComponentInjector {

    private static JvBeansContainer container;
    
    private static JvBindingDtoFactory factory;
    
    static {
        container = new JvBeansContainer();
        factory = new JvBindingDtoFactory();
        factory.setJvBeansContainer(container);
    }
    
    /**
     * コンストラクタ
     */
    private JvComponentInjector() {
    }
    
    /**
     * 指定したパッケージ配下に属するクラスから、JV-Dataのバインディング情報を取得してコンテナに保持させます。
     * <p>クラスパスに通っていれば、ファイルシステム・Jarファイルどちらでもかまいません（Jarファイルが優先されます）。
     * @param dtoPackage 走査対象パッケージ
     */
    public static void init(String dtoPackage) {
        container.init(dtoPackage);
    }
    
    /**
     * 一意な{@link JvBeansContainer}を返します。
     * <p>（注）対象のクラスローダー上での完全なシングルトンを保証するものではありません。
     * @return {@link JvBeansContainer}
     */
    public static JvBeansContainer getSingletonContainer() {
        synchronized (container) {
            return container;
        }
    }
    
    /**
     * 一意な{@link JvBindingDtoFactory}を返します。
     * <p>（注）対象のクラスローダー上での完全なシングルトンを保証するものではありません。
     * @return {@link JvBeansContainer}
     */
    public static JvBindingDtoFactory getSingletonFactory() {
        synchronized (factory) {
            return factory;
        }
    }
    
    /**
     * {@link JvLinkManager}を生成します。
     * <p>必要なプロパティが設定された状態で、prototypeとして生成されます。
     * @return {@link JvLinkManager}
     */
    public static JvLinkManager createJvLinkManager() {
        JvLinkManagerImpl jvLinkManager = new JvLinkManagerImpl();
        synchronized (container) {
            jvLinkManager.setJvBeansContainer(container);    
        }
        synchronized (factory) {
            jvLinkManager.setJvBindingDtoFactory(factory);    
        }
        return jvLinkManager;
    }

}
