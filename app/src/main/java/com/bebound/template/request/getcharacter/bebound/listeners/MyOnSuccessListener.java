package com.bebound.template.request.getcharacter.bebound.listeners;

import android.util.Log;
import android.widget.Toast;

import com.bebound.sdk.engine.listener.request.OnSuccessListener;
import com.bebound.template.R;

public class MyOnSuccessListener {

    /**
     * OnSuccessListener implementation
     */
    public static OnSuccessListener getListener() {
        return (context, request, response) -> {

            Log.d(MyOnSuccessListener.class.getSimpleName(), "The request was a success");

            /* NOTE: Get the response value by using the type and key associated to the response in the Be-App Manifest. */
            int id = request.getParameters().getInt("id", 0);
            String charName = response.getParameters().getString("char_name", "");
            String planetName = response.getParameters().getString("planet_name", "");

            // NOTE: length would be 0 if the response doesn't contains data.
            Log.d(MyOnSuccessListener.class.getSimpleName(), "But the request had some issue. It doesn't contains --length--");
            String toast = String.format(context.getString(R.string.toast_success), id, charName, planetName);

            Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
        };
    }
}
