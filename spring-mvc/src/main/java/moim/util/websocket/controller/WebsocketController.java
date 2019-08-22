package moim.util.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebsocketController {
	@RequestMapping(value="account/websocket",  method=RequestMethod.GET)
	public String socket() {
		return "account/groupChat";
	}
}