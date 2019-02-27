package com.appgramming.temporedit;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

class SystemUtils {

    /**
     * Copies a text to the clipboard.
     */
    static void copyToClipboard(Context context, String label, String text) {
        final ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            final ClipData clip = ClipData.newPlainText(label, text);
            clipboard.setPrimaryClip(clip);
        }
    }

    /**
     * Starts a Send Intent to share some text.
     */
    static void shareText(Context context, String text, String chooserTitle) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, chooserTitle));
    }

    /**
     * Gets the current text from the clipboard.
     */
    public static String pasteTextFromClipboard(Context context) {
        final ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if ((clipboard != null) && clipboard.hasPrimaryClip()) {

            // Get the current primary clip on the clipboard
            final ClipData clip = clipboard.getPrimaryClip();
            if ((clip != null) && (clip.getItemCount() > 0)) {

                final ClipDescription description = clip.getDescription();

                // Return null if the clipboard does not contain plain text or html text
                if (!description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) &&
                        !description.hasMimeType(ClipDescription.MIMETYPE_TEXT_HTML)) {
                    return null;
                }

                // Get the text from the clipboard
                final CharSequence sequence = clip.getItemAt(0).getText();
                if (sequence != null) {
                    return sequence.toString();
                }
            }
        }

        return null;
    }

//                case R.id.action_undo:
//            mEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.META_CTRL_ON | KeyEvent.META_CTRL_LEFT_ON));
//                mEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_Z));
//                mEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_Z));
//                mEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.META_CTRL_ON | KeyEvent.META_CTRL_LEFT_ON));
//
////                mEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
////                        KeyEvent.KEYCODE_Z, 0, KeyEvent.META_CTRL_LEFT_ON));
////                mEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
////                        KeyEvent.KEYCODE_Z, 0, KeyEvent.META_CTRL_LEFT_ON));
////                mEditText.dispatchKeyEvent(keyEvent(KeyEvent.KEYCODE_V, KeyEvent.META_CTRL_ON | KeyEvent.META_CTRL_LEFT_ON));
////                mEditText.dispatchKeyEvent(keyEvent(KeyEvent.KEYCODE_Z, 0));
//                return true;

//    public static KeyEvent keyEvent(int keycode, int metaState) {
//        final long currentTime = System.currentTimeMillis();
//        return new KeyEvent(currentTime, currentTime, KeyEvent.ACTION_DOWN, keycode, 0, metaState);
//    }
}
