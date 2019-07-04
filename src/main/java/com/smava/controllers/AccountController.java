package com.smava.controllers;

import com.smava.entities.Account;
import com.smava.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Account controller.
 */
@Controller
public class AccountController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * List all accounts.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("accounts", accountService.listAllAccounts());
        System.out.println("Returning rpoducts:");
        return "accounts";
    }

    /**
     * View a specific account by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("account/{id}")
    public String showAccount(@PathVariable Integer id, Model model) {
        model.addAttribute("account", accountService.getAccountById(id));
        return "accountshow";
    }

    @RequestMapping("account/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("account", accountService.getAccountById(id));
        return "accountform";
    }

    /**
     * New account.
     *
     * @param model
     * @return
     */
    @RequestMapping("account/new")
    public String newAccount(Model model) {
        model.addAttribute("account", new Account());
        return "accountform";
    }

    /**
     * Save account to database.
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "account", method = RequestMethod.POST)
    public String saveAccount(Account account) {
        accountService.saveAccount(account);
        return "redirect:/account/" + account.getId();
    }

    /**
     * Delete account by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("account/delete/{id}")
    public String delete(@PathVariable Integer id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }

}
