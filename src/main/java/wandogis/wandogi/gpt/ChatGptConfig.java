package wandogis.wandogi.gpt;

public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String API_KEY = "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";
    public static final String MODEL = "gpt-3.5-turbo-0301"; // 모델 변경
    public static final Integer MAX_TOKEN = 300;
    public static final Double TEMPERATURE = 0.0;
    public static final Double TOP_P = 1.0;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String URL = "https://api.openai.com/v1/chat/completions";

}
