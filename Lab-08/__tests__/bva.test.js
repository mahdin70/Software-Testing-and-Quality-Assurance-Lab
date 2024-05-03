const validateBankingInput = require("../validate");

describe("Boundary Value Analysis", () => {
  it("TC01", () => {
    expect(() =>
      validateBankingInput(100, 500, 5500, "C4567F", "Check status")
    ).not.toThrow();
  });
  it("TC02", () => {
    expect(() =>
      validateBankingInput(500, 201, 5500, "C4567F", "Deposit")
    ).not.toThrow();
  });
  it("TC03", () => {
    expect(() =>
      validateBankingInput(500, 500, 9999, "C4567F", "Check status")
    ).not.toThrow();
  });
  it("TC04", () => {
    expect(() =>
      validateBankingInput(500, 500, 5500, "99998Z", "Withdraw")
    ).toThrow();
  });
  it("TC05", () => {
    expect(() =>
      validateBankingInput(500, 500, 5500, "C4567F", "Deposit1")
    ).toThrow();
  });
});
