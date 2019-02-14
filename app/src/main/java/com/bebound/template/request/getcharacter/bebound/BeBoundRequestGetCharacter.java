package com.bebound.template.request.getcharacter.bebound;

import com.bebound.sdk.BeBound;
import com.bebound.sdk.engine.request.builder.RequestBuilder;
import com.bebound.template.request.getcharacter.bebound.listeners.MyOnFailListener;
import com.bebound.template.request.getcharacter.bebound.listeners.MyOnSuccessListener;
import com.bebound.template.request.getcharacter.bebound.listeners.MySentListener;

public class BeBoundRequestGetCharacter {

    /**
     * Manage the request
     * @param id is added to the Be-Bound request
     */
    public static void sendRequest(int id) {
        RequestBuilder request = BeBound.newRequest()
                                        // NOTE: Add the 'operationName' from the Be-App Manifest.
                                        .withOperationName("get_character")
                                        // NOTE: Add the request 'name' from the Be-App Manifest.
                                        .put("id", id)
                                        // NOTE: Set listeners to handle the response.
                                        .onSent(MySentListener.getListener())
                                        .onFailed(MyOnFailListener.getListener())
                                        .onSuccess(MyOnSuccessListener.getListener());
        // NOTE: Sends the request.
        request.send();
    }
}
