package com.example

import com.example.data.tariff.BangladeshResidentialTariff
import com.example.data.tariff.BangladeshSmallCommercialTariff
import com.example.domain.calculator.ElectricityBillCalculator
import com.example.domain.model.AppLanguage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal

class ElectricityBillCalculatorTest {

    private val resPlan = BangladeshResidentialTariff.plan
    private val commPlan = BangladeshSmallCommercialTariff.plan

    @Test
    fun testZeroUnits() {
        val result = ElectricityBillCalculator.calculate(
            units = 0.0,
            plan = resPlan,
            sanctionedLoadKw = 1.0,
            includeDemandCharge = true
        )
        assertEquals(BigDecimal("0.00"), result.energyCharge)
        assertEquals(BigDecimal("42.00"), result.demandCharge)
        // VAT on 42 is 42 * 0.05 = 2.10
        assertEquals(BigDecimal("2.10"), result.vatAmount)
        assertEquals(BigDecimal("44.10"), result.totalAmount)
        assertEquals(44L, result.totalRounded)
    }

    @Test
    fun testZeroUnitsWithoutDemandCharge() {
        val result = ElectricityBillCalculator.calculate(
            units = 0.0,
            plan = resPlan,
            sanctionedLoadKw = 0.0,
            includeDemandCharge = false
        )
        assertEquals(BigDecimal("0.00"), result.energyCharge)
        assertEquals(BigDecimal("0.00"), result.demandCharge)
        assertEquals(BigDecimal("0.00"), result.vatAmount)
        assertEquals(BigDecimal("0.00"), result.totalAmount)
    }

    @Test
    fun testLifelineUnits() {
        // 1 unit: 1 * 4.63 = 4.63
        val result1 = ElectricityBillCalculator.calculate(1.0, resPlan, includeDemandCharge = false)
        assertTrue(result1.isLifeline)
        assertEquals(BigDecimal("4.63"), result1.energyCharge)

        // 10 units: 10 * 4.63 = 46.30
        val result10 = ElectricityBillCalculator.calculate(10.0, resPlan, includeDemandCharge = false)
        assertTrue(result10.isLifeline)
        assertEquals(BigDecimal("46.30"), result10.energyCharge)

        // 50 units (exact boundary of lifeline): 50 * 4.63 = 231.50
        val result50 = ElectricityBillCalculator.calculate(50.0, resPlan, includeDemandCharge = false)
        assertTrue(result50.isLifeline)
        assertEquals(BigDecimal("231.50"), result50.energyCharge)
    }

    @Test
    fun testCrossLifelineToSlab1() {
        // 51 units: crosses lifeline, normal progressive slab applies (0 to 75 @ 5.26)
        // 51 * 5.26 = 268.26
        val result51 = ElectricityBillCalculator.calculate(51.0, resPlan, includeDemandCharge = false)
        assertFalse(result51.isLifeline)
        assertEquals(BigDecimal("268.26"), result51.energyCharge)

        // 75 units (boundary of slab 1): 75 * 5.26 = 394.50
        val result75 = ElectricityBillCalculator.calculate(75.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("394.50"), result75.energyCharge)
    }

    @Test
    fun testSlab2Calculations() {
        // 76 units: 75 * 5.26 + 1 * 7.20 = 394.50 + 7.20 = 401.70
        val result76 = ElectricityBillCalculator.calculate(76.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("401.70"), result76.energyCharge)

        // 100 units: 75 * 5.26 + 25 * 7.20 = 394.50 + 180.00 = 574.50
        val result100 = ElectricityBillCalculator.calculate(100.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("574.50"), result100.energyCharge)

        // 200 units (boundary of slab 2): 75 * 5.26 + 125 * 7.20 = 394.50 + 900.00 = 1294.50
        val result200 = ElectricityBillCalculator.calculate(200.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("1294.50"), result200.energyCharge)
    }

    @Test
    fun testSlab3Calculations() {
        // 201 units: 1294.50 + 1 * 7.59 = 1302.09
        val result201 = ElectricityBillCalculator.calculate(201.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("1302.09"), result201.energyCharge)

        // 300 units: 1294.50 + 100 * 7.59 = 1294.50 + 759.00 = 2053.50
        val result300 = ElectricityBillCalculator.calculate(300.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("2053.50"), result300.energyCharge)
    }

    @Test
    fun testSlab4Calculations() {
        // 301 units: 2053.50 + 1 * 8.02 = 2061.52
        val result301 = ElectricityBillCalculator.calculate(301.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("2061.52"), result301.energyCharge)

        // 400 units: 2053.50 + 100 * 8.02 = 2053.50 + 802.00 = 2855.50
        val result400 = ElectricityBillCalculator.calculate(400.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("2855.50"), result400.energyCharge)
    }

    @Test
    fun testSlab5And6Calculations() {
        // 500 units: 2855.50 + 100 * 12.67 = 2855.50 + 1267.00 = 4122.50
        val result500 = ElectricityBillCalculator.calculate(500.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("4122.50"), result500.energyCharge)

        // 600 units: 2855.50 + 200 * 12.67 = 2855.50 + 2534.00 = 5389.50
        val result600 = ElectricityBillCalculator.calculate(600.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("5389.50"), result600.energyCharge)

        // 1000 units: 5389.50 + 400 * 14.61 = 5389.50 + 5844.00 = 11233.50
        val result1000 = ElectricityBillCalculator.calculate(1000.0, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("11233.50"), result1000.energyCharge)
    }

    @Test
    fun testDecimalUnits() {
        // 100.5 units: 75 * 5.26 + 25.5 * 7.20 = 394.50 + 183.60 = 578.10
        val result = ElectricityBillCalculator.calculate(100.5, resPlan, includeDemandCharge = false)
        assertEquals(BigDecimal("578.10"), result.energyCharge)
    }

    @Test
    fun testVatAndDemandCharge() {
        // 100 units with 1 kW demand charge
        // Energy: 574.50
        // Demand: 42.00
        // Subtotal: 616.50
        // VAT (5%): 616.50 * 0.05 = 30.825 -> rounds to 30.83
        // Total: 647.33
        val result = ElectricityBillCalculator.calculate(100.0, resPlan, sanctionedLoadKw = 1.0, includeDemandCharge = true)
        assertEquals(BigDecimal("574.50"), result.energyCharge)
        assertEquals(BigDecimal("42.00"), result.demandCharge)
        assertEquals(BigDecimal("30.83"), result.vatAmount)
        assertEquals(BigDecimal("647.33"), result.totalAmount)
        assertEquals(647L, result.totalRounded)
    }

    @Test
    fun testSmallCommercialPlan() {
        // 100 units at flat rate 13.01 = 1301.00
        // Demand charge: 84.00 (1 kW)
        // Subtotal: 1385.00
        // VAT: 1385.00 * 0.05 = 69.25
        // Total: 1454.25
        val result = ElectricityBillCalculator.calculate(100.0, commPlan, sanctionedLoadKw = 1.0, includeDemandCharge = true)
        assertEquals(BigDecimal("1301.00"), result.energyCharge)
        assertEquals(BigDecimal("84.00"), result.demandCharge)
        assertEquals(BigDecimal("69.25"), result.vatAmount)
        assertEquals(BigDecimal("1454.25"), result.totalAmount)
    }

    @Test
    fun testBengaliDigitConversion() {
        assertEquals("০", ElectricityBillCalculator.toBengaliDigits("0"))
        assertEquals("১০০", ElectricityBillCalculator.toBengaliDigits("100"))
        assertEquals("২,৮৫০", ElectricityBillCalculator.toBengaliDigits("2,850"))
    }

    @Test
    fun testCurrencyFormatting() {
        val amount = BigDecimal("2850.50")
        assertEquals("৳ 2,850.50", ElectricityBillCalculator.formatCurrency(amount, AppLanguage.ENGLISH))
        assertEquals("৳ ২,৮৫০.৫০", ElectricityBillCalculator.formatCurrency(amount, AppLanguage.BANGLA))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNegativeUnitsThrows() {
        ElectricityBillCalculator.calculate(-5.0, resPlan)
    }
}
