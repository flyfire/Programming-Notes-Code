package com.ztiany.progress.manager;

import android.os.Parcel;
import android.os.Parcelable;

public class ProgressInfo implements Parcelable {

    private long currentBytes; //当前已上传或下载的总长度
    private long contentLength; //数据总长度
    private long eachBytes; //本次调用距离上一次被调用的间隔时间内上传或下载的byte长度
    private long intervalTime; //本次调用距离上一次被调用所间隔的时间(毫秒)
    private boolean finish; //进度是否完成

    void setCurrentBytes(long currentBytes) {
        this.currentBytes = currentBytes;
    }

    void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    void setEachBytes(long eachBytes) {
        this.eachBytes = eachBytes;
    }

    void setFinish(boolean finish) {
        this.finish = finish;
    }

    public long getCurrentBytes() {
        return currentBytes;
    }

    public long getContentLength() {
        return contentLength;
    }

    public long getIntervalTime() {
        return intervalTime;
    }

    public long getEachBytes() {
        return eachBytes;
    }

    public boolean isFinish() {
        return finish;
    }

    /**
     * 获取百分比,该计算舍去了小数点,如果你想得到更精确的值,请自行计算
     *
     * @return
     */
    public int getPercent() {
        if (getCurrentBytes() <= 0 || getContentLength() <= 0) return 0;
        return (int) ((100 * getCurrentBytes()) / getContentLength());
    }

    /**
     * 获取上传或下载网络速度,单位为byte/s,如果你想得到更精确的值,请自行计算
     *
     * @return
     */
    public long getSpeed() {
        if (getEachBytes() <= 0 || getIntervalTime() <= 0) return 0;
        return getEachBytes() * 1000 / getIntervalTime();
    }

    @Override
    public String toString() {
        return "ProgressInfo{" +
                ", currentBytes=" + currentBytes +
                ", contentLength=" + contentLength +
                ", eachBytes=" + eachBytes +
                ", intervalTime=" + intervalTime +
                ", finish=" + finish +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.currentBytes);
        dest.writeLong(this.contentLength);
        dest.writeLong(this.eachBytes);
        dest.writeLong(this.intervalTime);
        dest.writeByte(this.finish ? (byte) 1 : (byte) 0);
    }

    ProgressInfo() {

    }

    protected ProgressInfo(Parcel in) {
        this.currentBytes = in.readLong();
        this.contentLength = in.readLong();
        this.eachBytes = in.readLong();
        this.intervalTime = in.readLong();
        this.finish = in.readByte() != 0;
    }

    public static final Creator<ProgressInfo> CREATOR = new Creator<ProgressInfo>() {
        @Override
        public ProgressInfo createFromParcel(Parcel source) {
            return new ProgressInfo(source);
        }

        @Override
        public ProgressInfo[] newArray(int size) {
            return new ProgressInfo[size];
        }
    };
}
