//package business.order;
//
//import api.ApiException;
//import business.book.Book;
//import business.book.BookDao;
//import business.cart.ShoppingCart;
//import business.customer.CustomerForm;
//
//import java.time.DateTimeException;
//import java.time.YearMonth;
//import java.util.Date;
//
//public class DefaultOrderService implements OrderService {
//
//	private BookDao bookDao;
//
//	public void setBookDao(BookDao bookDao) {
//		this.bookDao = bookDao;
//	}
//
//	@Override
//	public OrderDetails getOrderDetails(long orderId) {
//		// NOTE: THIS METHOD PROVIDED NEXT PROJECT
//		return null;
//	}
//
//	@Override
//    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {
//
//		validateCustomer(customerForm);
//		validateCart(cart);
//
//		// NOTE: MORE CODE PROVIDED NEXT PROJECT
//
//		return -1;
//	}
//
//
//	private void validateCustomer(CustomerForm customerForm) {
//
//    	String name = customerForm.getName();
//
//		if (name == null || name.equals("") || name.length() < 4 || name.length() > 45) {
//			throw new ApiException.ValidationFailure("Invalid name field");
//		}
//
//		// Validate address
//		String address = customerForm.getAddress();
//		if (address == null || address.length() < 4 || address.length() > 45) {
//			throw new ApiException.ValidationFailure("Invalid address field");
//		}
//
//		// Validate phone
//		String phone = customerForm.getPhone();
//		if (phone == null || phone.isEmpty()) {
//			throw new ApiException.ValidationFailure("Invalid phone field");
//		}
//		// Remove spaces and dashes
//		phone = phone.replaceAll("[\\s-]", "");
//		if (!phone.matches("\\d{10}")) {
//			throw new ApiException.ValidationFailure("Invalid phone number");
//		}
//
//		// Validate email
//		String email = customerForm.getEmail();
//		if (email == null ||email.contains(" ") || !email.contains("@") || email.endsWith(".")) {
//			throw new ApiException.ValidationFailure("Invalid email field");
//		}
//
//		// Validate credit card number
//		String ccNumber = customerForm.getCcNumber();
//		if (ccNumber == null || ccNumber.isEmpty()) {
//			throw new ApiException.ValidationFailure("Invalid credit card number");
//		}
//		// Remove spaces and dashes
//		ccNumber = ccNumber.replaceAll("[\\s-]", "");
//		if (ccNumber.length() < 14 || ccNumber.length() > 16) {
//			throw new ApiException.ValidationFailure("Invalid credit card number");
//		}
//
//		if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
//			throw new ApiException.ValidationFailure("Please enter a valid expiration date.");
//
//		}
//	}
//
//	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
//		try {
//			int month = Integer.parseInt(ccExpiryMonth);
//			int year = Integer.parseInt(ccExpiryYear);
//			YearMonth expiryDate = YearMonth.of(year, month);
//			YearMonth currentMonth = YearMonth.now();
//
//			return expiryDate.isBefore(currentMonth);
//		} catch (NumberFormatException | DateTimeException e) {
//			return true;
//		}
//	}
//
//	private void validateCart(ShoppingCart cart) {
//
//		if (cart.getItems().size() <= 0) {
//			throw new ApiException.ValidationFailure("Cart is empty.");
//		}
//
//		cart.getItems().forEach(item-> {
//			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
//				throw new ApiException.ValidationFailure("Invalid quantity");
//			}
//			Book databaseBook = bookDao.findByBookId(item.getBookId());
//			// TODO: complete the required validations
//			if (databaseBook == null || item.getBookForm().getPrice() != databaseBook.price()
//					|| !(item.getBookForm().getCategoryId() == (databaseBook.categoryId()))) {
//				throw new ApiException.ValidationFailure("Invalid cart item");
//			}
//		});
//	}
//
//}
package business.order;

import api.ApiException;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.customer.CustomerForm;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Date;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		// NOTE: THIS METHOD PROVIDED NEXT PROJECT
		return null;
	}

	@Override
	public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		// NOTE: MORE CODE PROVIDED NEXT PROJECT

		return -1;
	}


	private void validateCustomer(CustomerForm customerForm) {

		String name = customerForm.getName();

		if (name == null || name.length()< 4 || name.length() > 45) {
			throw new ApiException.ValidationFailure("name", "Invalid name field");
		}

		// TODO: Validation checks for address, phone, email, ccNumber

		String address = customerForm.getAddress();
		if (address == null || address.length() < 4 || address.length() > 45) {
			throw new ApiException.ValidationFailure("address", "Address field is required");
		}

		String phone = customerForm.getPhone();

		if (phone == null) {
			throw new ApiException.ValidationFailure("phone", "Invalid phone field");
		}

		String phoneDigits = phone.replaceAll("\\D", "");
		if(phoneDigits.length()!=10)
		{
			throw new ApiException.ValidationFailure("phone", "Invalid phone field");
		}

		String email = customerForm.getEmail();
		if (email == null || email.isEmpty() || email.contains(" ") || !email.contains("@") || email.endsWith(".")) {
			throw new ApiException.ValidationFailure("email", "Invalid email format");
		}

		String ccNumber = customerForm.getCcNumber();
		if (ccNumber == null || ccNumber.isEmpty()) {
			throw new ApiException.ValidationFailure("ccNumber", "Credit card number is required");
		}

		String ccNumberDigitsOnly = ccNumber.replaceAll("\\D", "");
		int ccNumberLength = ccNumberDigitsOnly.length();
		if (ccNumberLength < 14 || ccNumberLength > 16) {
			throw new ApiException.ValidationFailure("ccNumber", "Invalid credit card number length");
		}

		if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.ValidationFailure("Please enter a valid expiration date.");

		}

	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {

		// TODO: return true when the provided month/year is before the current month/yeaR
		// HINT: Use Integer.parseInt and the YearMonth class
//		return false;

		if (ccExpiryMonth == null || ccExpiryYear == null) {
			return true; // If month or year is missing, consider it invalid
		}

		// Get the current year and month
		YearMonth currentYearMonth = YearMonth.now();

		try {
			int expiryYear = Integer.parseInt(ccExpiryYear);
			int expiryMonth = Integer.parseInt(ccExpiryMonth);

			// Create a YearMonth instance for the provided expiry date
			YearMonth expiryYearMonth = YearMonth.of(expiryYear, expiryMonth);

			// Check if the provided expiry date is before the current date
			return expiryYearMonth.isBefore(currentYearMonth);
		} catch (NumberFormatException | DateTimeException e) {
			return true; // If parsing fails, consider it invalid
		}

	}
//
//	private void validateCart(ShoppingCart cart) {
//
//		if (cart.getItems().size() <= 0) {
//			throw new ApiException.ValidationFailure("Cart is empty.");
//		}
//
//		cart.getItems().forEach(item-> {
//			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
//				throw new ApiException.ValidationFailure("Invalid quantity");
//			}
//			Book databaseBook = bookDao.findByBookId(item.getBookId());
//			// TODO: complete the required validations
//
//		});
//	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.ValidationFailure("Cart is empty.");
		}

		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
				throw new ApiException.ValidationFailure("Invalid quantity");
			}
			Book databaseBook = bookDao.findByBookId(item.getBookId());
			// TODO: complete the required validations
			if (databaseBook == null || item.getBookForm().getPrice() != databaseBook.price()
					|| !(item.getBookForm().getCategoryId() == (databaseBook.categoryId()))) {
				throw new ApiException.ValidationFailure("Invalid cart item");
			}
		});
	}

}
