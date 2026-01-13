package net.dima.prac;

import net.dima.prac.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class PracApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "testuser")
	void testCreateRoomAndFollowRedirect() throws Exception {
		// 1. Create Room (POST)
		MvcResult result = mockMvc.perform(post("/game/room")
						.param("title", "Test Room")
						.param("password", "")
                        .with(csrf()))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andReturn();

		String redirectedUrl = result.getResponse().getRedirectedUrl();

		// 2. Follow Redirect (GET)
		mockMvc.perform(get(redirectedUrl))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("game/room"));
	}
}
