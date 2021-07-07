package me.tamsil.springsecurityform.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("임의의 유저가 로그인을 한 상태")
    public void index_anonymous() throws Exception {
        mockMvc.perform(get("/").with(anonymous()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("현재 tamsil 유저가 로그인을 한 상태에서 호출했을 경우")
    public void index_user() throws Exception {
        mockMvc.perform(get("/").with(user("tamsil").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("현재 admin 유저가 로그인을 한 상태에서 호출했을 경우")
    public void index_admin() throws Exception {
        mockMvc.perform(get("/").with(user("admin").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("어드민 페이지에 일반 유저가 접근했을 경우")
    public void admin_user() throws Exception {
        mockMvc.perform(get("/admin").with(user("tamsil").roles("USER")))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("어드민 페이지에 어드민 유저가 접근했을 경우")
    public void admin_admin() throws Exception {
        mockMvc.perform(get("/admin").with(user("tamsil").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName("임의의 유저가 로그인을 한 상태 - 애노테이션을 이용한 방법")
    public void index_anonymous_annotation() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "tamsil", roles = "USER")
    @DisplayName("현재 tamsil 유저가 로그인을 한 상태에서 호출했을 경우 - 애노테이션을 이용한 방법")
    public void index_user_annotation() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUser
    @DisplayName("어드민 페이지에 일반 유저가 접근했을 경우 - 커스텀 애노테이션을 이용한 방법")
    public void admin_user_annotation() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "tamsil", roles = "ADMIN")
    @DisplayName("어드민 페이지에 어드민 유저가 접근했을 경우 - 애노테이션을 이용한 방법")
    public void admin_admin_annotation() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("폼 로그인 인증 테스트")
    @Transactional
    public void login_success() throws Exception {
        String username = "tamsil";
        String password = "123";
        Account user = createUser(username, password);
        mockMvc.perform(formLogin().user(user.getUsername()).password(password))
                .andExpect(authenticated());
    }

    @Test
    @DisplayName("폼 로그인 인증 테스트2")
    @Transactional
    public void login_success2() throws Exception {
        String username = "tamsil";
        String password = "123";
        Account user = createUser(username, password);
        mockMvc.perform(formLogin().user(user.getUsername()).password(password))
                .andExpect(authenticated());
    }

    @Test
    @DisplayName("폼 로그인 인증 테스트 - 실패케이스")
    public void login_fail() throws Exception {
        String username = "tamsil";
        String password = "123";
        Account user = createUser(username, password);
        mockMvc.perform(formLogin().user(user.getUsername()).password("12345"))
                .andExpect(unauthenticated());
    }

    private Account createUser(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole("USER");
        return accountService.createNew(account);
    }
}
