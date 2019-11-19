package SVVV.SVIIT.SSM;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String item_id,name,category,company;
    private String weight,price,discount;

    public Item(){

    }

    protected Item(Parcel in) {
        item_id = in.readString();
        name = in.readString();
        category = in.readString();
        company = in.readString();
        weight = in.readString();
        price = in.readString();
        discount = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
//    public Item(String item_id, String name, String category, String company) {
//        this.item_id = item_id;
//        this.name = name;
//        this.category = category;
//        this.company = company;
//    }

    public Item(String item_id, String name, String category, String company, String weight, String price, String discount) {
        this.item_id = item_id;
        this.name = name;
        this.category = category;
        this.company = company;
        this.weight = weight;
        this.price = price;
        this.discount = discount;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(item_id);
        parcel.writeString(name);
        parcel.writeString(category);
        parcel.writeString(company);
        parcel.writeString(weight);
        parcel.writeString(price);
        parcel.writeString(discount);
    }
}
