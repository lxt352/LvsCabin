package com.wiseme.lvscabin.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class ExpressInfo implements Parcelable {

    @SerializedName("context")
    private String content;
    private String ftime;
    private String time;

    private ExpressInfo(Parcel in) {
        content = in.readString();
        ftime = in.readString();
        time = in.readString();
    }

    public static final Parcelable.Creator<ExpressInfo> CREATOR =
            new Parcelable.Creator<ExpressInfo>() {

                public ExpressInfo createFromParcel(Parcel in) {
                    return new ExpressInfo(in);
                }

                public ExpressInfo[] newArray(int size) {
                    return new ExpressInfo[size];
                }
            };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(ftime);
        dest.writeString(time);
    }

    public String getContent() {
        return content;
    }

    public String getFtime() {
        return ftime;
    }

    public String getTime() {
        return time;
    }


    @Override
    public String toString() {
        return "ExpressInfo{" +
                "content='" + content + '\'' +
                ", ftime='" + ftime + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
