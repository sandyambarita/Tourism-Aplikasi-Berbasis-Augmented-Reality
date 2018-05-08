package praktikum.develops.a11414001.digitourapplication.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RESTClient {
    //public static String URL = "http://192.168.42.192/" ;
    public static String URL = "http://10.0.2.2/";
    private static DigitourAPI REST_CLIENT;
    static { //dieksekusi sebelum constructor, tapi hanya sekali untuk semua instans
        setupRestClient();
    }
    private RESTClient() {}
    public static DigitourAPI get() {
        return REST_CLIENT;
    }
    private static void setupRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory. create())
                .build();
        REST_CLIENT = retrofit.create(DigitourAPI. class);
    }

    }

