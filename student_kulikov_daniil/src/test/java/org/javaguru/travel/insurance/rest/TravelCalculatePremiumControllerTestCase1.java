package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase1 extends TravelCalculatePremiumControllerTestCase {

    @Test
    void  execute() throws Exception {
        executeAndCompare();
    }


    @Override
    protected String getTestCaseFolderName() {
        return "test_case_1";
    }
}
