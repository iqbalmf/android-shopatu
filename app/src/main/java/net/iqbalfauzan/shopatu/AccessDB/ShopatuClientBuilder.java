package net.iqbalfauzan.shopatu.AccessDB;

public class ShopatuClientBuilder {
    public static final String BASE_URL = "http://petbotz.com/";

    public static Api apiService(){
        return ShopatuClient.getClient(BASE_URL).create(Api.class);
    }
}
