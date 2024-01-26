package kr.co.icia.mapline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeckGlController {

		@GetMapping("/deckgl/sample")
		public String getDeckglSample() {
			return "deckgl/sample";
		}
}
