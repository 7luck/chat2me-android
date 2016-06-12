package io.sevenluck.client.chat2me.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import io.sevenluck.client.chat2me.R;
import io.sevenluck.client.chat2me.client.HttpResult;
import io.sevenluck.client.chat2me.domain.Chat;
import io.sevenluck.client.chat2me.tasks.AddChatAsyncTask;
import io.sevenluck.client.chat2me.tasks.callbacks.HttpRequestCallback;

/**
 * Created by loki on 6/12/16.
 */
public class AddChatDialog extends DialogFragment {

    private EditText chatTb;
    private NoticeDialogListener mListener;


    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.addchatdialog, null);
        chatTb = (EditText) view.findViewById(R.id.chat_name_tb);

        builder.setView(view)
                .setPositiveButton(R.string.dialog_join_chat_action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String chatName = chatTb.getText().toString();
                        Chat newChat = Chat.getInstance(chatName);
                        AddChatAsyncTask addChatTask = new AddChatAsyncTask(AddChatDialog.this.getContext() , new AddChatCallback());
                        try {
                            addChatTask.execute(newChat);
                        } catch (Exception e) {
                            Log.e("AddChatDialog", e.getMessage(), e);
                        }
                    }
                })
                .setNegativeButton(R.string.dialag_cancel_action, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddChatDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public void setNoticeListener(NoticeDialogListener mListener) {
        this.mListener = mListener;
    }

    public class AddChatCallback extends HttpRequestCallback<HttpResult<Chat>> {

        @Override
        public void onFishedRequest(HttpResult<Chat> result) {

            if (result.isSuceeded()) {
                mListener.onDialogPositiveClick(AddChatDialog.this);
            }

        }

    }
}
