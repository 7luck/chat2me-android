package io.sevenluck.client.chat2me.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import io.sevenluck.client.chat2me.R;
import io.sevenluck.client.chat2me.dialog.AddChatDialog;

import static android.R.layout.simple_list_item_1;

/**
 * Created by loki on 6/8/16.
 */
public class ChatRoomListFragment extends ListFragment implements AdapterView.OnItemClickListener, AddChatDialog.NoticeDialogListener {

    public ChatRoomListFragment() {}

    private FloatingActionButton mAddChatBtn;
    private AddChatDialog mAddChatDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatroom, container, false);

        mAddChatBtn = (FloatingActionButton) view.findViewById(R.id.addChatBtn);
        mAddChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    public void showDialog(){
        FragmentManager fm = getFragmentManager();
        mAddChatDialog = new AddChatDialog();
        mAddChatDialog.setNoticeListener(this);
        mAddChatDialog.show(fm, "addchatdialog");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), simple_list_item_1, new String[] {"1", "2"});
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(getContext().getApplicationContext(), "ChatTo angelegt.", Toast.LENGTH_LONG).show();
        mAddChatDialog.dismiss();
    }


}
