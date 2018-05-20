package cn.pompip.webdrawable;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class WebPageInfo implements Parcelable {
    public String title;
    public Bitmap bitmap;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.bitmap, flags);
    }

    public WebPageInfo() {
    }

    protected WebPageInfo(Parcel in) {
        this.title = in.readString();
        this.bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<WebPageInfo> CREATOR = new Creator<WebPageInfo>() {
        @Override
        public WebPageInfo createFromParcel(Parcel source) {
            return new WebPageInfo(source);
        }

        @Override
        public WebPageInfo[] newArray(int size) {
            return new WebPageInfo[size];
        }
    };
}
