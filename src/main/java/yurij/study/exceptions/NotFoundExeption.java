package yurij.study.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception class.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundExeption extends RuntimeException {
}
