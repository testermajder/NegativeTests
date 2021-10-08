package com.projectname.api.client.asserts;

import com.projectname.api.client.utils.Dates;

public class AssertHelper {
    public static boolean verifyWithTolerance(long tolerance, String actualDate, String expectedDate)
    {
        long timeDiff = 0;
        timeDiff = Dates.parseToClientFormatDateTime(actualDate).toLocalTime().toSecondOfDay() -
                Dates.parseToClientFormatDateTime(expectedDate).toLocalTime().toSecondOfDay();
        if (timeDiff > tolerance) {
            return false;
        } else {
            return true;
        }
    }
}

