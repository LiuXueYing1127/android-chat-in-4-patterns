package nju.androidchat.client.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleableRes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import nju.androidchat.client.R;

public class ItemImageReceive extends LinearLayout {


    @StyleableRes
    int index0 = 0;

    private ImageView imageView;
    private Context context;
    private UUID messageId;
    private OnRecallMessageRequested onRecallMessageRequested;


    public ItemImageReceive(Context context, String image, UUID messageId) throws IOException {
        super(context);
        this.context = context;
        inflate(context, R.layout.item_image_receive, this);
        this.imageView = findViewById(R.id.chat_item_content_image);
        this.messageId = messageId;
        setImage(image);
    }

    public void init(Context context) {

    }

    public String getImage() {
        return imageView.getDrawable().toString();
    }

    public void setImage(String image) throws IOException {
        URL thumb_u = new URL(image);
        Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
        imageView.setImageDrawable(thumb_d);
    }
}
