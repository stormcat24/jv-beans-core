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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stormcat.jvbeans.response.JvContents;
import org.stormcat.jvbeans.response.JvCourseFile;
import org.stormcat.jvbeans.response.JvMvContents;
import org.stormcat.jvbeans.response.JvOpen;
import org.stormcat.jvbeans.response.JvResult;
import org.stormcat.jvbeans.util.JacobUtil;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.DispatchEvents;
import com.jacob.com.Variant;

/**
 * {@link JvLinkWrapper}の実装クラスです。
 * <p>純粋なJV-Link APIのJavaラッパーを提供します。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvLinkWrapperImpl implements JvLinkWrapper {
    
    /** ログを出力します。 */
    private static Logger logger = LoggerFactory.getLogger(JvLinkWrapperImpl.class);

    /** {@link ActiveXComponent}です。 */
    private ActiveXComponent activeXComponent;
    
    /** JvlinkのDLL名({@value})です。 */
    private static final String JVLINK_DLL = "JVDTLab.JVLink.1";

    public JvLinkWrapperImpl() {
        this.activeXComponent = new ActiveXComponent(JVLINK_DLL);
    }

    /**
     * {@inheritDoc}
     */
    public JvResult jvInit(String sid) {
        Variant variant = Dispatch.call(activeXComponent, "JVInit", sid);
        logger.info(String.format("JRA-VANサーバにアクセスする際に %s をUserAgentとして使用します。", sid));
        return JvResultFactory.createJvResult(variant);
    }

    /**
     * {@inheritDoc}
     */
    public JvResult jvSetUIProperties() {
        Variant variant = Dispatch.call(activeXComponent, "JVSetUIProperties");
        return JvResultFactory.createJvResult(variant);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvResult jvSetServiceKey(String serviceKey) {
        Variant variant = Dispatch.call(activeXComponent, "JVSetServiceKey", serviceKey);
        return JvResultFactory.createJvResult(variant);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvResult jvSetSaveFlag(long saveFlag) {
        Variant variant = Dispatch.call(activeXComponent, "JVSetSaveFlag", saveFlag);
        return JvResultFactory.createJvResult(variant);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvResult jvSetSavePath(String savePath) {
        Variant variant = Dispatch.call(activeXComponent, "JVSetSavePath", savePath);
        return JvResultFactory.createJvResult(variant);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvOpen jvOpen(String dataSpec, String fromTime, long option) {
        Variant readCount = JacobUtil.getRefVariant(Integer.class, "0");
        Variant downloadCount = JacobUtil.getRefVariant(Integer.class, "0");
        Variant lastFileTimeStamp = JacobUtil.getRefVariant(String.class);
        Variant variant = Dispatch.call(activeXComponent, "JVOpen", 
                dataSpec, fromTime, option,
                readCount, downloadCount, lastFileTimeStamp);
        JvOpen jvOpen = JvResultFactory.createJvResult(variant, JvOpen.class);
        jvOpen.setDownloadCount(downloadCount.getIntRef());
        jvOpen.setReadCount(readCount.getIntRef());
        jvOpen.setLastFileTimeStamp(lastFileTimeStamp.getStringRef());
        return jvOpen;
    }
    
    /**
     * {@inheritDoc}
     */
    public JvOpen jvRtOpen(String dataSpec, String key) {
        Variant variant = Dispatch.call(activeXComponent, "JVRTOpen", dataSpec, key);
        return JvResultFactory.createJvResult(variant, JvOpen.class);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvResult jvStatus() {
        Variant variant = Dispatch.call(activeXComponent, "JVStatus");
        return JvResultFactory.createJvResult(variant);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvContents<?> jvRead(long size) {
        Variant vBuff = JacobUtil.getRefVariant(String.class);
        Variant vFileName = JacobUtil.getRefVariant(String.class);
        Variant variant = Dispatch.call(activeXComponent, "JVRead", 
                vBuff, size, vFileName);
        JvContents<?> contents = JvResultFactory.createJvResult(variant, JvContents.class);
        contents.setFileName(vFileName.getStringRef());
        contents.setLine(vBuff.getStringRef().trim());
        return contents; 
    }
    
    /**
     * {@inheritDoc}
     */
    public void jvSkip() {
        Dispatch.call(activeXComponent, "JVSkip");
    }
    
    /**
     * {@inheritDoc}
     */
    public void jvCancel() {
        Dispatch.call(activeXComponent, "JVCancel");
    }

    /**
     * {@inheritDoc}
     */
    public JvResult jvClose() {
        Variant variant = Dispatch.call(activeXComponent, "JVClose");
        return JvResultFactory.createJvResult(variant);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvResult jvFileDelete(String fileName) {
        Variant variant = Dispatch.call(activeXComponent, "JVFileDelete", fileName);
        return JvResultFactory.createJvResult(variant);
    }

    /**
     * {@inheritDoc}
     */
    public JvResult jvFukuFile(String pattern, String filePath) {
        Variant variant = Dispatch.call(activeXComponent, "JVFukuFile", pattern, filePath);
        return JvResultFactory.createJvResult(variant);
    }

    /**
     * {@inheritDoc}
     */
    public JvResult jvMvCheck(String key) {
        Variant variant = Dispatch.call(activeXComponent, "JVMVCheck", key);
        return JvResultFactory.createJvResult(variant);
    }

    /**
     * {@inheritDoc}
     */
    public JvResult jvMvPlay(String key) {
        Variant variant = Dispatch.call(activeXComponent, "JVMVPlay", key);
        return JvResultFactory.createJvResult(variant);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvResult jvMvPlayWithTime(String movieType, String key) {
        Variant variant = Dispatch.call(activeXComponent, "JVMVPlayWithTime", movieType, key);
        return JvResultFactory.createJvResult(variant);
    }
    
    /**
     * {@inheritDoc}
     */
    public JvResult jvMvOpen(String movieType, String searchKey) {
        Variant variant = Dispatch.call(activeXComponent, "JVMVOpen", movieType, searchKey);
        return JvResultFactory.createJvResult(variant);
    }

    /**
     * {@inheritDoc}
     */
    public JvMvContents jvMvRead(long size) {
        Variant buff = JacobUtil.getRefVariant(String.class);
        Variant variant = Dispatch.call(activeXComponent, "JVMVRead", buff, size);
        JvMvContents result = JvResultFactory.createJvResult(variant, JvMvContents.class);
        result.setLine(buff.getStringRef());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public JvCourseFile jvCourseFile(String key) {
        Variant explanation = JacobUtil.getRefVariant(String.class);
        Variant filePath = JacobUtil.getRefVariant(String.class);
        Variant variant = Dispatch.call(activeXComponent, "JVCourseFile", 
                key, filePath, explanation);
        JvCourseFile courseFile = JvResultFactory.createJvResult(variant, JvCourseFile.class);
        courseFile.setFilePath(filePath.getStringRef());
        courseFile.setExplanation(explanation.getStringRef());
        return courseFile;
    }
    
    /**
     * {@inheritDoc}
     */
    public JvCourseFile jvCourceFile2(String key, String filePath) {
        Variant variant = Dispatch.call(activeXComponent, "JVCourseFile2", key, filePath);
        JvCourseFile courseFile = JvResultFactory.createJvResult(variant, JvCourseFile.class);
        courseFile.setFilePath(filePath);
        return courseFile;
    }

    /**
     * {@inheritDoc}
     */
	public void jvWatchEvent() {
		Dispatch jvlinkInterface = activeXComponent.getObject();
		DispatchEvents event =	 new DispatchEvents(jvlinkInterface, new JvLinkEvent(), JVLINK_DLL);
		Dispatch.callSub(jvlinkInterface, "JVWatchEvent");
		event.safeRelease();
	}

    /**
     * {@inheritDoc}
     */
	public JvResult jvWatchEventClose() {
        Variant variant = Dispatch.call(activeXComponent, "JVWatchEventClose");
        return JvResultFactory.createJvResult(variant);
	}
    
}
