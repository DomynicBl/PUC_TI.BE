import com.microsoft.azure.cognitiveservices.translator.TextTranslationClient;
import com.microsoft.azure.cognitiveservices.translator.TextTranslationClientImpl;
import com.microsoft.azure.cognitiveservices.translator.models.TranslationResult;
import com.microsoft.azure.cognitiveservices.translator.models.TranslationTextRequest;
import com.microsoft.rest.credentials.ApiKeyCredentials;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String apiKey = "5c3d6643781c442ab8adb43a30af3e64";
        String endpoint = "https://api.cognitive.microsofttranslator.com/";

        //Configurar as credenciais
        ApiKeyCredentials credentials = new ApiKeyCredentials(apiKey);

        //Criar o cliente de tradução
        TextTranslationClient client = TextTranslationClientImpl.authenticate(credentials).withEndpoint(endpoint);

        //Texto teste a ser traduzido
        String textoParaTraduzir = "Olá, mundo!";

        //Configurar a solicitação de tradução
        TranslationTextRequest request = new TranslationTextRequest()
                .addText(textoParaTraduzir)
                .to("en"); //Idioma de destino

        //Realizar a tradução
        List<TranslationResult> results = client.translateText(request);

        //Processar os resultados
        for (TranslationResult result : results) {
            System.out.println("Texto original: " + result.text());
            System.out.println("Tradução: " + result.translations().get(0).text());
        }
    }
}
