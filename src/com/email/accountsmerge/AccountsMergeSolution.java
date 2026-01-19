package com.email.accountsmerge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// Class representing solution for Accounts Merge problem
public class AccountsMergeSolution {

	private Map<String, String> parent = new HashMap<>();

	// Main logic: Merge accounts based on emails
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, String> emailToName = new HashMap<>();

		// Step 1: Initialize Union-Find parent map
		for (List<String> account : accounts) {
			String name = account.get(0);
			for (int i = 1; i < account.size(); i++) {
				String email = account.get(i);
				parent.putIfAbsent(email, email);
				emailToName.put(email, name);
			}
		}

		// Step 2: Union emails in the same account
		for (List<String> account : accounts) {
			String firstEmail = account.get(1);
			for (int i = 2; i < account.size(); i++) {
				union(firstEmail, account.get(i));
			}
		}

		// Step 3: Group emails by root parent
		Map<String, TreeSet<String>> groups = new HashMap<>();
		for (String email : parent.keySet()) {
			String root = find(email);
			groups.putIfAbsent(root, new TreeSet<>());
			groups.get(root).add(email);
		}

		// Step 4: Build result list
		List<List<String>> result = new ArrayList<>();
		for (String root : groups.keySet()) {
			List<String> mergedAccount = new ArrayList<>();
			mergedAccount.add(emailToName.get(root)); // Add name
			mergedAccount.addAll(groups.get(root)); // Add sorted emails
			result.add(mergedAccount);
		}

		return result;
	}

	// Find with path compression
	private String find(String email) {
		if (!parent.get(email).equals(email)) {
			parent.put(email, find(parent.get(email)));
		}
		return parent.get(email);
	}

	// Union two emails
	private void union(String a, String b) {
		String rootA = find(a);
		String rootB = find(b);
		if (!rootA.equals(rootB)) {
			parent.put(rootB, rootA);
		}
	}

	// Demo main method
	public static void main(String[] args) {
		AccountsMergeSolution solution = new AccountsMergeSolution();

		List<List<String>> accounts = new ArrayList<>();
		accounts.add(Arrays.asList("Rajesh", "rajeshpatil2846@mail.com", "rajesh1@mail.com"));
		accounts.add(Arrays.asList("Mohit", "mohit21@mail.com", "mohit2@mail.com"));
		accounts.add(Arrays.asList("priya", "priya@mail.com"));
		accounts.add(Arrays.asList("shiv", "shivdeva@mail.com"));

		List<List<String>> mergedAccounts = solution.accountsMerge(accounts);

		System.out.println("Merged Accounts:");
		for (List<String> account : mergedAccounts) {
			System.out.println(account);
		}
	}
}
