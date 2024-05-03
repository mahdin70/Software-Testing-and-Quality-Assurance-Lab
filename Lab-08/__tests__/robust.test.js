const validateBankingInput = require("../validate");

describe("Robust BVA", () => {
  it("TC01", () => {
    expect(() =>
      validateBankingInput(1000, 500, 5500, "C4567F", "Check status")
    ).toThrow("Please enter a blank or a three-digit number for area code.");
  });
  it("TC02", () => {
    expect(() =>
      validateBankingInput(500, 1000, 5500, "C4567F", "Deposit")
    ).toThrow(
      "Prefix must be a three-digit number not beginning with 0 or 1."
    );
  });
  it("TC03", () => {
    expect(() =>
      validateBankingInput(500, 500, 999, "C4567F", "Check status")
    ).toThrow("Invalid suffix. Suffix must be a four-digit number.");
  });
  it("TC04", () => {
    expect(() =>
      validateBankingInput(500, 500, 5500, "00000", "Withdraw")
    ).toThrow(
      "Invalid password. Password must be six characters long and alphanumeric."
    );
  });
  it("TC05", () => {
    expect(() =>
      validateBankingInput(500, 500, 5500, "C4567F", "Rizz")
    ).toThrow(
      "Invalid command. Available commands are 'Check status', 'Deposit', and 'Withdrawal'."
    );
  });
});
