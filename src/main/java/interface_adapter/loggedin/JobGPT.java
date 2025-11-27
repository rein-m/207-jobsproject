package interface_adapter.loggedin;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

// NOTE: You must ensure GEMINI_API_KEY is set in your environment or IDE config
public class JobGPT {

    private final Client client;
    private final String SYSTEM_INSTRUCTION;

    public JobGPT() throws Exception {
        // Initialize the client once in the constructor
        this.client = new Client();

        // Define the AI's core instructions
        this.SYSTEM_INSTRUCTION =
                "You are an HR ChatBot named \"JobGPT\". Your job is to answer questions for applicants " +
                        "and companies, and give professional advice. " +
                        "Your first task is to greet the user and ask them to provide this basic information: " +
                        "(1) The name of the user (Company or Applicant), (2) Resume/Company information, and (3) Preferred job (for applicants only). " +
                        "Once they provide this info, you can proceed to answer their questions.";
    }

    /**
     * Generates a response from the Gemini model based on the user's input.
     * @param userMessage The message received from the user via the UI.
     * @return The text response from the AI.
     */
    public String getInitialGreeting() throws Exception {
        // Use the system instruction as the prompt for the first call
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                this.SYSTEM_INSTRUCTION,
                null);
        return response.text();
    }

    /**
     * Generates a response from the Gemini model based on the user's input.
     * @param userMessage The message received from the user via the UI.
     * @return The text response from the AI.
     */
    public String generateResponse(String userMessage) throws Exception {
        // For simple chat, we prepend the instruction to the new message to maintain context.
        String fullPrompt = this.SYSTEM_INSTRUCTION + "\n\nUser Question: " + userMessage;

        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                fullPrompt,
                null);
        return response.text();
    }
}