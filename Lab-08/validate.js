function validateBankingInput(areaCode, prefix, suffix, password, command) {
  if (areaCode !== "" && (areaCode.length !== 3 || isNaN(areaCode))) {
    throw new Error(
      "Please enter a blank or a three-digit number for area code."
    );
  }

  if (
    prefix.length !== 3 ||
    isNaN(prefix) ||
    prefix[0] === "0" ||
    prefix[0] === "1"
  ) {
    throw new Error(
      "Prefix must be a three-digit number not beginning with 0 or 1."
    );
  }

  if (suffix.length !== 4 || isNaN(suffix)) {
    throw new Error("Invalid suffix. Suffix must be a four-digit number.");
  }

  if (password.length !== 6 || !/^[a-zA-Z0-9]+$/.test(password)) {
    throw new Error(
      "Invalid password. Password must be six characters long and alphanumeric."
    );
  }

  if (
    command !== "Check status" &&
    command !== "Deposit" &&
    command !== "Withdrawal"
  ) {
    throw new Error(
      "Invalid command. Available commands are 'Check status', 'Deposit', and 'Withdrawal'."
    );
  }

  return true;
}

module.exports = validateBankingInput;
