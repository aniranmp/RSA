package ir.aniranmp.web.util;


public class Route {


    public static final String API = "/api";
    public static final String API_PUBLIC = API + "/public";

    public static final String API_SORTING = API + "/sorting";
    public static final String API_SORTING_ALL = API_SORTING + "/all";
    public static final String API_SORTING_EXCEL = API_SORTING + "/excel";
    //Specified sort type
    public static final String API_SORTING_MERGE = API_SORTING + "/merge";
    public static final String API_SORTING_INSERTION = API_SORTING + "/insertion";
    public static final String API_SORTING_QUICK = API_SORTING + "/quick";
    public static final String API_SORTING_QUICK_DUAL = API_SORTING + "/quick_dual";
    public static final String API_SORTING_HEAP = API_SORTING + "/heap";
    public static final String API_SORTING_RADIX = API_SORTING + "/radix";
    public static final String API_SORTING_BUCKET = API_SORTING + "/bucket";
    public static final String API_SORTING_BUCKET_V2 = API_SORTING + "/bucketv2";
    public static final String API_SORTING_MERGE_PARALLEL = API_SORTING_MERGE + "/parallel";
    public static final String API_SORTING_MERGE_PARALLEL_CORE = API_SORTING_MERGE + "/parallelCore";
    //Cryptography
    public static final String API_CRYPTO = API + "/crypto";
    public static final String API_CRYPTO_ENCRYPT = API_CRYPTO + "/encrypt";
    public static final String API_CRYPTO_DECRYPT = API_CRYPTO + "/decrypt";
    public static final String API_CRYPTO_BRUTEFORCE = API_CRYPTO + "/bruteforce";

    public static final String API_GENERATE_RANDOM_LONG = API + "/random";
}