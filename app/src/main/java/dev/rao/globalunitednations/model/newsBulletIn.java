package dev.rao.globalunitednations.model;

import android.os.Parcel;
import android.os.Parcelable;

public class newsBulletIn implements Parcelable {

    String title, image, detailUrl;

    public newsBulletIn(String title, String image, String detailUrl) {
        this.title = title;
        this.image = image;
        this.detailUrl = detailUrl;
    }

    protected newsBulletIn(Parcel in){
        title = in.readString();
        image = in.readString();
        detailUrl = in.readString();
    }

    public static final Creator<newsBulletIn> CREATOR = new Creator<newsBulletIn>() {
        @Override
        public newsBulletIn createFromParcel(Parcel in) {
            return new newsBulletIn(in);
        }

        @Override
        public newsBulletIn[] newArray(int size) {
            return new newsBulletIn[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public final Creator<newsBulletIn> creator = new Creator<newsBulletIn>() {
        @Override
        public newsBulletIn createFromParcel(Parcel source) {
            return new newsBulletIn(source);
        }

        @Override
        public newsBulletIn[] newArray(int size) {
            return new newsBulletIn[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(detailUrl);

    }
}
