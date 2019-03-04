/*
 * Temporedit
 * Copyright (C) 2019 Appgramming. All rights reserved.
 * https://www.appgramming.com
 */
package com.appgramming.temporedit;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

class Utils {

    /**
     * Copies a text to the clipboard.
     */
    @SuppressWarnings("SameParameterValue")
    static void copyToClipboard(Context context, int labelResId, String text) {
        final ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            final ClipData clip = ClipData.newPlainText(context.getString(labelResId), text);
            clipboard.setPrimaryClip(clip);
        }
    }

    /**
     * Starts a Send Intent to share some text.
     */
    @SuppressWarnings("SameParameterValue")
    static void shareText(Context context, String text, int chooserTitleResId) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, context.getString(chooserTitleResId)));
    }

    /**
     * Gets the current text from the clipboard.
     */
    static String pasteTextFromClipboard(Context context) {
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

    /**
     * Starts an activity to view an url.
     */
    static void viewUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }
}
