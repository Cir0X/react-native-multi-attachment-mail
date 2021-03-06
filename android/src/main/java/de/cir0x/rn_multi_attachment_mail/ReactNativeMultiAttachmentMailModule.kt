package de.cir0x.rn_multi_attachment_mail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.text.Html
import android.text.Spanned
import com.facebook.react.bridge.*
import java.io.File

class ReactNativeMultiAttachmentMailModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "RNReactNativeMultiAttachmentMail"
    }

    @ReactMethod
    public fun mail(options: ReadableMap, callback: Callback) {
        val mailIntent = Intent(Intent.ACTION_SEND_MULTIPLE)
        mailIntent.type = "message/rfc822"

        if (doesKeyExist(KEY_SUBJECT, options)) {
            val subject = options.getString(KEY_SUBJECT)
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        }

        if (doesKeyExist(KEY_BODY, options)) {
            val body = if (doesKeyExist(KEY_IS_HTML, options)) {
                options.getString(KEY_BODY).toHtml()
            } else {
                options.getString(KEY_BODY)
            }
            mailIntent.putExtra(Intent.EXTRA_TEXT, body)
        }

        if (doesKeyExist(KEY_RECIPIENTS, options)) {
            val recipients = options.getArray(KEY_RECIPIENTS)
            mailIntent.putExtra(Intent.EXTRA_EMAIL, recipients.getStringArray())
        }

        if (doesKeyExist(KEY_CC_RECIPIENTS, options)) {
            val ccRecipients = options.getArray(KEY_CC_RECIPIENTS)
            mailIntent.putExtra(Intent.EXTRA_CC, ccRecipients.getStringArray())
        }

        if (doesKeyExist(KEY_BCC_RECIPIENTS, options)) {
            val bccRecipients = options.getArray(KEY_BCC_RECIPIENTS)
            mailIntent.putExtra(Intent.EXTRA_BCC, bccRecipients.getStringArray())
        }

        if (doesKeyExist(KEY_ATTACHMENTS, options)) {
            val attachments = options.getArray(KEY_ATTACHMENTS)
            mailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, attachments.getContentUriArray())
        }

        // send intent
        reactContext.startActivity(Intent.createChooser(mailIntent, "Send mail..."))

    }

    private fun doesKeyExist(key: String, options: ReadableMap) = options.hasKey(key) && !options.isNull(key)

    private fun File.getContentUri() = FileProvider.getUriForFile(reactContext, "${reactContext.packageName}.fileprovider", this)

    @SuppressWarnings("deprecation")
    private fun String.toHtml(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }

    private fun ReadableArray.getStringArray() = (0 until this.size()).map {
        this.getString(it)
    }.toTypedArray()


    private fun ReadableArray.getContentUriArray(): ArrayList<Uri> = (0 until this.size()).map {
        val file = File(this.getString(it))
        file.getContentUri()
    }.toCollection(ArrayList())


    companion object {
        const val KEY_SUBJECT = "subject"
        const val KEY_BODY = "body"
        const val KEY_RECIPIENTS = "recipients"
        const val KEY_CC_RECIPIENTS = "ccRecipients"
        const val KEY_BCC_RECIPIENTS = "bccRecipients"
        const val KEY_ATTACHMENTS = "attachments"
        const val KEY_IS_HTML = "isHtml"
        const val KEY_PATH = "path"
    }
}