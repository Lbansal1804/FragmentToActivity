package com.example.lakhbir.afragmentcommunicatetoactivity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private EditText editText;
    private Button button;

    OnMessageReadListener messageReadListener; //callback for listener


    public MessageFragment() {
        // Required empty public constructor
    }

    public interface OnMessageReadListener {  //this is our interfaCE & IMP CONCEPT WHEN TALKING WITH ACTIVITY OR FRAGMENTS.
        //Now, check with interfaces is implwmwnted by parent activity or not. We can achieve this by onAttach method of fragent lifecycle
        public void onMessageRead(String message);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        editText = view.findViewById(R.id.txt_message);
        button = view.findViewById(R.id.bn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //i. give the message
                String message = editText.getText().toString();

                //ii.Send message to activity

                messageReadListener.onMessageRead(message);

            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        //from onAttach method we can get the callback for interface

        try{

            messageReadListener = (OnMessageReadListener) activity;
        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()+"must override onMessageRead.....");
        }
    }
}

//Finally, implement this interface on MAIN ACTIVITTY