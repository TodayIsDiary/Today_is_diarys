package com.example.today_is_diarys.filter;

import com.example.today_is_diarys.error.ErrorResponse;
import com.example.today_is_diarys.error.GlobalErrorCode;
import com.example.today_is_diarys.error.exception.BeargameException;
import com.example.today_is_diarys.error.properties.ErrorProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class GlobalErrorFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (BeargameException e) {
            setErrorResponse(e.getErrorProperties(), response);
        } catch (Exception e) {
            if (e.getCause() instanceof BeargameException) {
                setErrorResponse(((BeargameException) e.getCause()).getErrorProperties(), response);
            } else {
                setErrorResponse(GlobalErrorCode.INTERNAL_SERVER_ERROR, response);
            }
        }
    }


    private void setErrorResponse(ErrorProperties errorProperties, HttpServletResponse response) throws IOException {
        response.setStatus(errorProperties.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        new ErrorResponse(errorProperties)
                )
        );
    }
}
