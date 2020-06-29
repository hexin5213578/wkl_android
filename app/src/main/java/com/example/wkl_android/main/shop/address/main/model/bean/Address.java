package com.example.wkl_android.main.shop.address.main.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class Address implements Parcelable {

    /**
     * addressID : 360c421bf5f2411181fbdc4d3821ac68
     * consignee : 赵先生
     * phoneNumber : 13700000000
     * site1 : {"id":430000,"name":"湖南省","pid":0}
     * site2 : {"id":433100,"name":"湘西土家族苗族自治州","pid":430000}
     * site3 : {"id":433122,"name":"泸溪县","pid":433100}
     * detailedAddress : 县城
     * userId : 1
     * isDefault : false
     */

    private String addressId;
    private String consignee;
    private String phoneNumber;
    private Site1Bean site1;
    private Site2Bean site2;
    private Site3Bean site3;
    private String detailedAddress;
    private String addressDetail;
    private String addressReDetail;
    private int userId;
    private boolean isDefault;
    private String addressArea ; //	String	河南省郑州市二七区南屏路11


    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressReDetail() {
        return addressReDetail;
    }

    public void setAddressReDetail(String addressReDetail) {
        this.addressReDetail = addressReDetail;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID='" + addressId + '\'' +
                ", consignee='" + consignee + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", site1=" + site1 +
                ", site2=" + site2 +
                ", site3=" + site3 +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", userId=" + userId +
                ", isDefault=" + isDefault +
                '}';
    }

    public Address() {

    }

    protected Address(Parcel in) {

        addressId = in.readString();
        addressReDetail = in.readString();
        addressDetail = in.readString();
        consignee = in.readString();
        phoneNumber = in.readString();
        addressArea = in.readString();
        detailedAddress = in.readString();
        userId = in.readInt();
        isDefault = in.readByte() != 0;
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getAddressID() {
        return addressId;
    }

    public void setAddressID(String addressID) {
        this.addressId = addressID;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Site1Bean getSite1() {
        return site1;
    }

    public void setSite1(Site1Bean site1) {
        this.site1 = site1;
    }

    public Site2Bean getSite2() {
        return site2;
    }

    public void setSite2(Site2Bean site2) {
        this.site2 = site2;
    }

    public Site3Bean getSite3() {
        return site3;
    }

    public void setSite3(Site3Bean site3) {
        this.site3 = site3;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addressId);
        dest.writeString(addressReDetail);
        dest.writeString(addressDetail);
        dest.writeString(consignee);
        dest.writeString(phoneNumber);
        dest.writeString(addressArea);
        dest.writeString(detailedAddress);
        dest.writeInt(userId);
        dest.writeByte((byte) (isDefault ? 1 : 0));
    }

    public static class Site1Bean implements Parcelable {
        /**
         * id : 430000
         * name : 湖南省
         * pid : 0
         */

        private String id;
        private String name;
        private int pid;

        protected Site1Bean(Parcel in) {
            id = in.readString();
            name = in.readString();
            pid = in.readInt();
        }

        public static final Creator<Site1Bean> CREATOR = new Creator<Site1Bean>() {
            @Override
            public Site1Bean createFromParcel(Parcel in) {
                return new Site1Bean(in);
            }

            @Override
            public Site1Bean[] newArray(int size) {
                return new Site1Bean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeInt(pid);
        }
    }

    public static class Site2Bean implements Parcelable {
        /**
         * id : 433100
         * name : 湘西土家族苗族自治州
         * pid : 430000
         */

        private String id;
        private String name;
        private int pid;

        protected Site2Bean(Parcel in) {
            id = in.readString();
            name = in.readString();
            pid = in.readInt();
        }

        public static final Creator<Site2Bean> CREATOR = new Creator<Site2Bean>() {
            @Override
            public Site2Bean createFromParcel(Parcel in) {
                return new Site2Bean(in);
            }

            @Override
            public Site2Bean[] newArray(int size) {
                return new Site2Bean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeInt(pid);
        }
    }

    public static class Site3Bean implements Parcelable {
        /**
         * id : 433122
         * name : 泸溪县
         * pid : 433100
         */

        private String id;
        private String name;
        private int pid;

        protected Site3Bean(Parcel in) {
            id = in.readString();
            name = in.readString();
            pid = in.readInt();
        }

        public static final Creator<Site3Bean> CREATOR = new Creator<Site3Bean>() {
            @Override
            public Site3Bean createFromParcel(Parcel in) {
                return new Site3Bean(in);
            }

            @Override
            public Site3Bean[] newArray(int size) {
                return new Site3Bean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeInt(pid);
        }
    }
}
