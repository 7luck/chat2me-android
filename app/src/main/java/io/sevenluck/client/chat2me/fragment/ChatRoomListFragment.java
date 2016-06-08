package io.sevenluck.client.chat2me.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import io.sevenluck.client.chat2me.R;

import static android.R.layout.simple_list_item_1;

/**
 * Created by loki on 6/8/16.
 */
public class ChatRoomListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public ChatRoomListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatroom, container, false);
        return view;
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

}
