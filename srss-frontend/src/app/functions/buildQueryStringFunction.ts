import {HttpParams} from '@angular/common/http';


export function buildQueryString(
  params: Map<string, any>
): string {
  let httpParams = new HttpParams();

  params.forEach((value, key) => {
    if (value !== null && value !== undefined) {
      httpParams = httpParams.set(key, String(value));
    }
  });

  const serialized = httpParams.toString();
  return serialized ? `?${serialized}` : '';
}
