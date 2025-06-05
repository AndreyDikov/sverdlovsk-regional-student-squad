export function isPast(dateStr: string): boolean {
  const d = new Date(dateStr);
  if (isNaN(d.getTime())) {
    // Невалидная строка даты — тут можно вернуть false или кинуть ошибку
    return false;
  }
  return d < new Date();
}
