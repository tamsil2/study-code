package me.kobeshow.springbootjpashop.controller;

import lombok.RequiredArgsConstructor;
import me.kobeshow.springbootjpashop.domain.Member;
import me.kobeshow.springbootjpashop.domain.item.Item;
import me.kobeshow.springbootjpashop.service.ItemService;
import me.kobeshow.springbootjpashop.service.MemberService;
import me.kobeshow.springbootjpashop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.bind.annotation.XmlElementDecl;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }
}
