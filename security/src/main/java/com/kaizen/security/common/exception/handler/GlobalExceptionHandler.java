package com.kaizen.security.common.exception.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kaizen.security.common.dto.ApiResponse;
import com.kaizen.security.common.exception.custom.AccessDeniedException;
import com.kaizen.security.common.exception.custom.AuthenticationException;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthException(AuthenticationException ex) {
        log.warn("Authentication exception occurred: {} " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.UNAUTHORIZED.value(), // 401
                "Token expired or invalid",
                null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // 🔸 For access denied (authenticated but no rights)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDenied(AccessDeniedException ex) {
        log.warn("Access denied: {} " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.FORBIDDEN.value(), // 403
                "Access denied",
                null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // COMMENT: Exceptions from Spring Data JPA and Spring Security that we want to handle globally

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.warn("Data Integrity Violation: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Données invalides. Merci de vérifier les contraintes sur les champs.",
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolation(ConstraintViolationException ex) {
        log.warn("Constraint Violation: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Données invalides. Merci de vérifier les contraintes sur les champs.",
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ApiResponse<String>> handleTransactionSystem(TransactionSystemException ex) {
        log.warn("Transaction System Exception: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Une erreur est survenue lors de l'enregistrement des données.",
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<ApiResponse<String>> handleJpaSystem(JpaSystemException ex) {
        log.warn("JPA System Exception: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Une erreur est survenue lors de l'enregistrement des données.",
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ApiResponse<String>> handleOptimisticLocking(OptimisticLockingFailureException ex) {
        log.warn("Optimistic Locking Exception: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.CONFLICT.value(),
                "Conflit lors de la mise à jour des données. Veuillez réessayer.",
                null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<String>> handleNullPointerException(NullPointerException ex) {
        log.warn("Null Pointer Exception: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erreur technique", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException ex) {
        log.warn("Runtime exception occurred: {}:" + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erreur technique", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidDataAccess(InvalidDataAccessApiUsageException ex) {
        log.warn("Invalid Data Access Exception: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erreur lors du traitement des données. Veuillez contacter l’administrateur.", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("Illegal Argument Exception: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Données invalides. Merci de vérifier les contraintes sur les champs.",
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

     //COMMENT: GLOBAL EXCEPTION
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthorizationDenied(AuthorizationDeniedException ex) {
        log.warn("Authorization denied: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.FORBIDDEN.value(),
                "You do not have permission to access this resource",
                null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        log.warn("Exception: " + ex.getMessage(), ex);
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Une erreur est survenue",
                null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
