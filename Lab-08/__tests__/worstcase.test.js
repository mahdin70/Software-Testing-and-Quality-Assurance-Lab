const validateBankingInput = require("../validate");

describe("Worst Case BVA", () => {
  it("TC01", () => {
    expect(() =>
      validateBankingInput(99, 199, 5500, "C4567F", "Check status")
    ).toThrow("Please enter a blank or a three-digit number for area code.");
  });
  it("TC02", () => {
    expect(() =>
      validateBankingInput(500, 500, 999, "00000", "Deposit")
    ).toThrow("Invalid suffix. Suffix must be a four-digit number.");
  });
  it("TC03", () => {
    expect(() =>
      validateBankingInput(500, 500, 999, "C4567F", "Check status")
    ).toThrow("Invalid suffix. Suffix must be a four-digit number.");
  });
  it("TC04", () => {
    expect(() =>
      validateBankingInput(500, 500, 10000, "ZZZZZZ", "Withdraw")
    ).toThrow("Invalid suffix. Suffix must be a four-digit number.");
  });
  it("TC05", () => {
    expect(() =>
      validateBankingInput(500, 199, 5500, "C4567F", "Rizz")
    ).toThrow("Prefix must be a three-digit number not beginning with 0 or 1.");
  });
});
