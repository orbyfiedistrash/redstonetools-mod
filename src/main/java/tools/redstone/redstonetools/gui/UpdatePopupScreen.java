package tools.redstone.redstonetools.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.net.URI;

import static tools.redstone.redstonetools.RedstoneToolsClient.MOD_VERSION;

public class UpdatePopupScreen extends PopupScreen {

    URI uri;
    
    public UpdatePopupScreen(Screen parent, URI uri, String newVersion) {
        super(parent, "Update Available",
                "An update is available for redstone tools! You are on version " + MOD_VERSION + " but version " + newVersion + " is available.");
        this.uri = uri;
    }

    @Override
    protected void addButtons(int y) {
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 5, y, 150, 20, Text.of("Go to release"), (button) -> {
            Util.getOperatingSystem().open(uri);

            this.close();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, y, 150, 20, Text.of("Ignore"), (button) -> {
            this.close();
        }));
    }
}
