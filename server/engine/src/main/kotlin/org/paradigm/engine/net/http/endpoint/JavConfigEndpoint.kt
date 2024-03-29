package org.paradigm.engine.net.http.endpoint

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.handler.codec.http.QueryStringDecoder
import org.paradigm.engine.net.http.sendHttpText

object JavConfigEndpoint {

    fun handle(ctx: ChannelHandlerContext, msg: FullHttpRequest, query: QueryStringDecoder) {
        ctx.sendHttpText(JAV_CONFIG_STRING.toByteArray(Charsets.ISO_8859_1))
    }

    private const val JAV_CONFIG_STRING = "title=Old School RuneScape\n" +
            "adverturl=http://www.runescape.com/g=oldscape/bare_advert.ws\n" +
            "codebase=http://127.0.0.1/\n" +
            "cachedir=oldschool\n" +
            "storebase=0\n" +
            "initial_jar=gamepack_5156984.jar\n" +
            "initial_class=client.class\n" +
            "termsurl=http://www.jagex.com/g=oldscape/terms/terms.ws\n" +
            "privacyurl=http://www.jagex.com/g=oldscape/privacy/privacy.ws\n" +
            "viewerversion=124\n" +
            "win_sub_version=1\n" +
            "mac_sub_version=2\n" +
            "other_sub_version=2\n" +
            "browsercontrol_win_x86_jar=browsercontrol_0_-1928975093.jar\n" +
            "browsercontrol_win_amd64_jar=browsercontrol_1_1674545273.jar\n" +
            "download=2129476\n" +
            "window_preferredwidth=800\n" +
            "window_preferredheight=600\n" +
            "advert_height=96\n" +
            "applet_minwidth=765\n" +
            "applet_minheight=503\n" +
            "applet_maxwidth=5760\n" +
            "applet_maxheight=2160\n" +
            "msg=lang0=English\n" +
            "msg=tandc=This game is copyright © 1999 - 2022 Jagex Ltd.\\Use of this game is subject to our [\"http://www.runescape.com/terms/terms.ws\"Terms and Conditions] and [\"http://www.runescape.com/privacy/privacy.ws\"Privacy Policy].\n" +
            "msg=options=Options\n" +
            "msg=language=Language\n" +
            "msg=changes_on_restart=Your changes will take effect when you next start this program.\n" +
            "msg=loading_app_resources=Loading application resources\n" +
            "msg=err_verify_bc64=Unable to verify browsercontrol64\n" +
            "msg=err_verify_bc=Unable to verify browsercontrol\n" +
            "msg=err_load_bc=Unable to load browsercontrol\n" +
            "msg=loading_app=Loading application\n" +
            "msg=err_create_target=Unable to create target applet\n" +
            "msg=err_create_advertising=Unable to create advertising\n" +
            "msg=err_save_file=Error saving file\n" +
            "msg=err_downloading=Error downloading\n" +
            "msg=ok=OK\n" +
            "msg=cancel=Cancel\n" +
            "msg=message=Message\n" +
            "msg=copy_paste_url=Please copy and paste the following URL into your web browser\n" +
            "msg=information=Information\n" +
            "msg=err_get_file=Error getting file\n" +
            "msg=new_version=Update available! You can now launch the client directly from the OldSchool website.\\nGet the new version from the link on the OldSchool homepage: http://oldschool.runescape.com/\n" +
            "msg=new_version_linktext=Open OldSchool Homepage\n" +
            "msg=new_version_link=http://oldschool.runescape.com/\n" +
            "param=9=ElZAIrq5NpKCzNJ-DSENi-zwPnB28lNmU7-nCgPS56YJsbIzYBRMqQ\n" +
            "param=11=https://127.0.0.1/\n" +
            "param=19=196515767263-1oo20deqm6edn7ujlihl6rpadk9drhva.apps.googleusercontent.com\n" +
            "param=20=https://127.0.0.1/\n" +
            "param=22=0\n" +
            "param=12=1\n" +
            "param=13=.runescape.com\n" +
            "param=16=false\n" +
            "param=2=https://payments.jagex.com/\n" +
            "param=1=1\n" +
            "param=15=0\n" +
            "param=3=false\n" +
            "param=7=0\n" +
            "param=18=\n" +
            "param=17=http://127.0.0.1/world_list.ws\n" +
            "param=10=5\n" +
            "param=4=1\n" +
            "param=6=0\n" +
            "param=5=0\n" +
            "param=21=0\n" +
            "param=14=0\n" +
            "param=8=true"
}