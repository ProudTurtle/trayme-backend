package pl.infirsoft.trayme.controller;

import org.springframework.core.io.ClassPathResource
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.charset.StandardCharsets

@RestController
class TestController {

    @GetMapping("/")
    fun showBanner(): String {
        val bannerResource = ClassPathResource("banner.txt")
        val bannerStream = bannerResource.inputStream
        val bannerText = StreamUtils.copyToString(bannerStream, StandardCharsets.UTF_8)

        return "<html><body><pre><code>$bannerText</code></pre></body></html>"
    }
}
