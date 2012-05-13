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

import java.io.File;
import java.util.Date;
import java.util.regex.Pattern;

import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.lang.DateUtil;
import org.stormcat.jvbeans.common.lang.StringUtil;
import org.stormcat.jvbeans.config.DataOption;
import org.stormcat.jvbeans.config.Environment;
import org.stormcat.jvbeans.config.condition.RealTimeOpenCondition;
import org.stormcat.jvbeans.config.condition.StoredOpenCondition;
import org.stormcat.jvbeans.config.key.CourseFileKey;
import org.stormcat.jvbeans.config.key.RtOpenKey;
import org.stormcat.jvbeans.exception.JvBeansRuntimeException;
import org.stormcat.jvbeans.jvlink.analyze.JvBeansContainer;
import org.stormcat.jvbeans.jvlink.analyze.JvBindingDtoFactory;
import org.stormcat.jvbeans.response.JvCourseFile;
import org.stormcat.jvbeans.response.JvOpen;
import org.stormcat.jvbeans.response.JvResult;


/**
 * {@link JvLinkManager}の実装クラスです。
 * <p>各API内部では入力チェックや型変換等の処理を行った後に、{@link JvLinkWrapper}に処理を委譲します。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvLinkManagerImpl implements JvLinkManager {
    
    /** UserAgentの最大バイト長（{@value}）です。 */
    private static final int MAX_BYTE_LENGTH_USER_AGENT = 64;
    
    /** open時に指定するための時間フォーマットです。 */
    private static final String JV_DATE_FORMAT = "yyyyMMddHHmmss";
    
    /** UserAgentです。 */
    private String userAgent = "UNKNOWN";
    
    /** {@link JvLinkWrapper}です。 */
    private JvLinkWrapper jvLinkWrapper;
    
    /** {@link JvBeansContainer}です。 */
    private JvBeansContainer jvBeansContainer;
    
    /** {@link JvBindingDtoFactory}です。 */
    private JvBindingDtoFactory jvBindingDtoFactory;
    
    /** {@link JvLinkEventHandler} */
    private JvLinkEventHandler jvLinkEventHandler;
    
    /**
     * 
     * コンストラクタ
     */
    public JvLinkManagerImpl() {
        jvLinkWrapper = new JvLinkWrapperImpl();
    }

    /**
     * {@inheritDoc}
     */
    public void cancel() {
        jvLinkWrapper.jvCancel();
    }

    /**
     * {@inheritDoc}
     */
    public JvResult close() {
        JvResult result = jvLinkWrapper.jvClose();
        JvLinkExceptionHandler.handleException(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public JvResult fileDelete(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("ファイル名がnullになっています。");
        }
        JvResult result = jvLinkWrapper.jvFileDelete(fileName);
        JvLinkExceptionHandler.handleException(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public JvResult fukuFile(String pattern, String filePath) {
        if (pattern == null) {
            throw new IllegalArgumentException("服色標示がnullになっています。");
        }
        if (filePath == null) {
            throw new IllegalArgumentException("保存パスがnullになっています。");
        }
        if (!new File(filePath).exists()) {
            throw new IllegalArgumentException("保存パスは実際に存在するディレクトリを指定する必要があります。");
        }
        JvResult result = jvLinkWrapper.jvFukuFile(pattern, filePath); 
        JvLinkExceptionHandler.handleException(result);
        return result; 
    }

    /**
     * {@inheritDoc}
     */
    public JvResult init() {
        JvResult result = jvLinkWrapper.jvInit(getUserAgent());
        JvLinkExceptionHandler.handleException(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public <T extends JvBindingDto> JvReader<T> open(StoredOpenCondition<T> condition, Date fromTime, DataOption dataOption) {
        if (fromTime == null) {
            throw new IllegalArgumentException("データ読み出し開始ポイントがnullになっています。");
        }
        return open(condition, DateUtil.formatDate(fromTime, JV_DATE_FORMAT), dataOption);
    }
    
    /**
     * {@inheritDoc}
     */
    public <T extends JvBindingDto> JvReader<T> open(StoredOpenCondition<T> condition, String fromTime, DataOption dataOption) {
        if (condition == null) {
            throw new IllegalArgumentException("データ検索条件がnullになっています。");
        }
        if (fromTime == null) {
            throw new IllegalArgumentException("データ読み出し開始ポイントがnullになっています。");
        }
        if (dataOption == null) {
            dataOption = DataOption.STANDARD;
        }
        
        JvOpen jvOpen = jvLinkWrapper.jvOpen(condition.getDataSpec().getValue(), 
                fromTime, dataOption.getValue());
        JvLinkExceptionHandler.handleException(jvOpen);
        return new JvBindingDtoReader<T>(this, condition);
    }
    
    /**
     * {@inheritDoc}
     */
    public <T extends JvBindingDto> JvReader<String> simpleOpen(StoredOpenCondition<T> condition, Date fromTime,
            DataOption dataOption) {
        if (fromTime == null) {
            throw new IllegalArgumentException("データ読み出し開始ポイントがnullになっています。");
        }
        return simpleOpen(condition, DateUtil.formatDate(fromTime, JV_DATE_FORMAT), dataOption);
    }
    
    /**
     * {@inheritDoc}
     */
    public <T extends JvBindingDto> JvReader<String> simpleOpen(StoredOpenCondition<T> condition, String fromTime,
            DataOption dataOption) {
        if (condition == null) {
            throw new IllegalArgumentException("データ検索条件がnullになっています。");
        }
        if (fromTime == null) {
            throw new IllegalArgumentException("データ読み出し開始ポイントがnullになっています。");
        }
        if (dataOption == null) {
            dataOption = DataOption.STANDARD;
        }
        
        JvOpen jvOpen = jvLinkWrapper.jvOpen(condition.getDataSpec().getValue(), 
                fromTime, dataOption.getValue());
        JvLinkExceptionHandler.handleException(jvOpen);
        return new JvSimpleReader<T>(this, condition);
    }
    
    /**
     * {@inheritDoc}
     */
    public <T extends JvBindingDto> JvReader<T> rtOpen(RealTimeOpenCondition<T> condition, RtOpenKey rtOpenKey) {
        if (condition == null) {
            throw new IllegalArgumentException("データ検索条件がnullになっています。。");
        }
        if (rtOpenKey == null) {
            throw new IllegalArgumentException("keyがnullになっています。");
        }
        JvOpen jvOpen = jvLinkWrapper.jvRtOpen(condition.getDataSpec().getValue(), rtOpenKey.toString());
        JvLinkExceptionHandler.handleException(jvOpen);      
        return new JvBindingDtoReader<T>(this, condition);
    }
    
    /**
     * {@inheritDoc}
     */
    public <T extends JvBindingDto> JvReader<String> rtSimpleOpen(RealTimeOpenCondition<T> condition, RtOpenKey rtOpenKey) {
        if (condition == null) {
            throw new IllegalArgumentException("データ検索条件がnullになっています。。");
        }
        if (rtOpenKey == null) {
            throw new IllegalArgumentException("keyがnullになっています。");
        }
        JvOpen jvOpen = jvLinkWrapper.jvRtOpen(condition.getDataSpec().getValue(), rtOpenKey.toString());
        JvLinkExceptionHandler.handleException(jvOpen);      
        return new JvSimpleReader<T>(this, condition);
    }

    /**
     * {@inheritDoc}
     */
    public JvResult setSaveFlag(boolean saveFlag) {
        JvResult result = jvLinkWrapper.jvSetSaveFlag(saveFlag ? 1 : 0);
        JvLinkExceptionHandler.handleException(result);  
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public JvResult setSavePath(String savePath) {
        if (savePath == null) {
            throw new IllegalArgumentException("保存パスがnullになっています。");
        }
        if (!new File(savePath).exists()) {
            throw new IllegalArgumentException("保存パスは実際に存在するディレクトリを指定する必要があります。");
        }
        JvResult result = jvLinkWrapper.jvSetSavePath(savePath);
        JvLinkExceptionHandler.handleException(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public JvResult setServiceKey(String serviceKey) {
        if (serviceKey == null) {
            throw new IllegalArgumentException("サービスキーがnullになっています。");
        }
        JvResult result = jvLinkWrapper.jvSetServiceKey(serviceKey);
        JvLinkExceptionHandler.handleException(result);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public JvResult setUIProperties() {
        JvResult result = jvLinkWrapper.jvSetUIProperties();
        JvLinkExceptionHandler.handleException(result);
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    public void skip() {
        jvLinkWrapper.jvSkip();
    }

    /**
     * {@inheritDoc}
     */
    public long status() {
        JvResult result = jvLinkWrapper.jvStatus();
        JvLinkExceptionHandler.handleException(result);
        return result.getReturnCode();
    }

    /**
     * {@inheritDoc}
     */
    public JvCourseFile courseFile(CourseFileKey courseFileKey) {
        if (courseFileKey == null) {
            throw new IllegalArgumentException("コースを指定するkeyがnullになっています。");
        }
        JvCourseFile courceFile = jvLinkWrapper.jvCourseFile(courseFileKey.toString());
        JvLinkExceptionHandler.handleException(courceFile);
        return courceFile;
    }

    /**
     * {@inheritDoc}
     */
    public JvCourseFile courseFile2(CourseFileKey courseFileKey, String filePath) {
        if (courseFileKey == null) {
            throw new IllegalArgumentException("コースを指定するkeyがnullになっています。");
        }
        JvCourseFile courceFile = jvLinkWrapper.jvCourceFile2(courseFileKey.toString(), filePath); 
        JvLinkExceptionHandler.handleException(courceFile);
        return courceFile;
    }

    /**
     * {@inheritDoc}
     */
	public void watchEvent() {
		if (jvLinkEventHandler == null) {
			throw new JvBeansRuntimeException("JvLinkManagerにJvLinkEventHandlerの実装をセットする必要があります。");
		}
		jvLinkWrapper.jvWatchEvent(jvLinkEventHandler);
	}

    /**
     * {@inheritDoc}
     */
	public JvResult watchEventClose() {
		return jvLinkWrapper.jvWatchEventClose();
	}
    
    /**
     * {@inheritDoc}
     */
    public void setUserAgent(String userAgent) {
        if (StringUtil.isEmpty(userAgent)) {
            throw new IllegalArgumentException("UserAgentにnullや空文字を指定することはできません。");
        }
        
        int length = StringUtil.getBytesLength(userAgent, Charset.WINDOWS31J);
        if (length > MAX_BYTE_LENGTH_USER_AGENT) {
            throw new IllegalArgumentException(
                    String.format("UserAgentは64バイト以下でなければいけません。: %s バイト", length));
        }
        
        if (!Pattern.matches("^[\\w\\s\\.\\/]*$", userAgent)) {
            throw new IllegalArgumentException(String.format("%s", userAgent));
        }
        
        this.userAgent = userAgent;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * {@link JvBeansContainer}を返します。
     * @return jvBeansContainer {@link JvBeansContainer}
     */
    public JvBeansContainer getJvBeansContainer() {
        return jvBeansContainer;
    }

    /**
     * {@link JvBeansContainer}をセットします。
     * @param jvBeansContainer {@link JvBeansContainer}
     */
    public void setJvBeansContainer(JvBeansContainer jvBeansContainer) {
        this.jvBeansContainer = jvBeansContainer;
    }

    /**
     * {@link JvBindingDtoFactory}を返します。
     * @return jvBindingDtoFactory {@link JvBindingDtoFactory}
     */
    public JvBindingDtoFactory getJvBindingDtoFactory() {
        return jvBindingDtoFactory;
    }

    /**
     * {@link JvBindingDtoFactory}をセットします。
     * @param jvBindingDtoFactory {@link JvBindingDtoFactory}
     */
    public void setJvBindingDtoFactory(JvBindingDtoFactory jvBindingDtoFactory) {
        this.jvBindingDtoFactory = jvBindingDtoFactory;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void setJvLinkEventHandler(JvLinkEventHandler jvLinkEventHandler) {
		this.jvLinkEventHandler = jvLinkEventHandler;
	}

	/**
     * {@link JvLinkWrapper}を返します。
     * @return jvLinkWrapper {@link JvLinkWrapper}
     */
    public JvLinkWrapper getJvLinkWrapper() {
        return jvLinkWrapper;
    }

}
