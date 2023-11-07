package com.pismo.transaction.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.transaction.HttpRequest;
import com.pismo.transaction.SpringIntegrationTest;
import com.pismo.transaction.resource.account.request.AccountCreateRequest;
import com.pismo.transaction.resource.transaction.request.TransactionCreateRequest;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class StepDefinition extends SpringIntegrationTest {

    private String payload;
    private ResponseEntity<?> response;
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    private final HttpRequest httpRequest;
    private final Map<String, String> mapParams = new HashMap<>();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(final Object fromValue, final Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

    @Given("database is clean")
    public void databaseIsClean () {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "tb_transaction", "tb_account");
    }

    @And("execute sqlFile in database with name {string}")
    public void executeSqlFileInDatabase(final String file) throws IOException {
        log.info("M=executeSqlFileInDatabase, sqlFile={}", file);

        final InputStream inputStream = new ClassPathResource("files/database/" + file).getInputStream();
        final InputStreamReader sqlReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        jdbcTemplate.execute(FileCopyUtils.copyToString(sqlReader));
    }

    @Given("create account request is build with new information:")
    public void createAccountRequestIsBuildWithNewInformation(final AccountCreateRequest request) throws JsonProcessingException {
        payload = objectMapper.writeValueAsString(request);
    }

    @Given("create transaction request is build with new information:")
    public void createTransactionRequestIsBuildWithNewInformation(final TransactionCreateRequest request) throws JsonProcessingException {
        payload = objectMapper.writeValueAsString(request);
    }

    @When("a Post request to resource {string} is made")
    public void aPostRequestToResourceIsMade(final String url) {
        log.info("M=aPostRequestToResourceIsMade url={}, payload={}", url, payload);
        response = httpRequest.doPostRequest(url, payload);
    }

    @When("a GET request to resource {string} is made")
    public void aGetRequestToResourceIsMade(final String url) {
        log.info("M=aGetRequestToResourceIsMade url={}", url);

        response = httpRequest.doGetRequest(url, mapParams);
    }

    @Then("it should return http status code {int}")
    public void itShouldReturnHttpStatusCode(final int expectedStatusCode) throws IOException {
        final int statusCode = response.getStatusCode().value();
        Object body = null;

        if (Objects.nonNull(response.getBody())) {
            body = objectMapper.readValue(response.getBody().toString(), Object.class);
        }

        log.info("M=itShouldReturnHttpStatusCode statusCode={}, body={}", statusCode, body);

        Assert.assertEquals(expectedStatusCode, statusCode);
    }

}
