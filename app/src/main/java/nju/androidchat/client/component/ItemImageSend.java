package nju.androidchat.client.component;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.StyleableRes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import lombok.Setter;
import nju.androidchat.client.R;

public class ItemImageSend extends LinearLayout implements View.OnLongClickListener {
    @StyleableRes
    int index0 = 0;

    private ImageView imageView;
    private Context context;
    private UUID messageId;
    @Setter private OnRecallMessageRequested onRecallMessageRequested;

    public ItemImageSend(Context context, String image, UUID messageId, OnRecallMessageRequested onRecallMessageRequested) throws IOException {
        super(context);
        this.context = context;
        inflate(context, R.layout.item_image_send, this);
        this.imageView = findViewById(R.id.chat_item_content_image);
        this.messageId = messageId;
        this.onRecallMessageRequested = onRecallMessageRequested;

        this.setOnLongClickListener(this);
        setImage(image);
    }

    public String getImage() {
        return imageView.getDrawable().toString();
    }

    public void setImage(String image) throws IOException {
        URL thumb_u = new URL(image);
        Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
        imageView.setImageDrawable(thumb_d);
    }

    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要撤回这条消息吗？")
                .setPositiveButton("是", (dialog, which) -> {
                    if (onRecallMessageRequested != null) {
                        onRecallMessageRequested.onRecallMessageRequested(this.messageId);
                    }
                })
                .setNegativeButton("否", ((dialog, which) -> {
                }))
                .create()
                .show();

        return true;


    }

}
