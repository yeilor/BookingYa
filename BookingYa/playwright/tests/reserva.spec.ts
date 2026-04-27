import { test, expect } from '@playwright/test';

test('crear reserva simulada', async ({ page }) => {
  await page.goto('https://example.com');

  await expect(page).toHaveTitle(/Example/);
});