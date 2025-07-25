# API

#### 1. Сущности
**Объявление (Advertisement)**

| Поле          | Бизнес-описание                               | Пример значения               |
|---------------|-----------------------------------------------|-------------------------------|
| title         | Заголовок объявления                          | "Требуются подшипники ГОСТ 3325" |
| description   | Детальное описание потребности/предложения    | "Новые, в упаковке, DIN 623"  |
| owner         | Идентификатор владельца (бизнес-аккаунта)     | "urn:company:uralmash"        |
| visibility    | Видимость: "public", "private", "partners"    | "public"                      |
| dealSide      | Тип сделки: "demand" (спрос), "proposal" (предложение) | "demand"               |
| productType   | Категория промышленного товара               | "подшипник", "крепеж"         |
| productId     | Идентификатор модели товара (опционально)     | "product:bearing:6308-2RS"    |

#### 2. Бизнес-функции (методы)
**CRUDS для объявлений**
1. **`ads.create`** - Создание нового объявления
   - *Бизнес-правило*: Только верифицированные компании могут создавать объявления
   - *Ограничения*: Минимум 3 символа в заголовке

2. **`ads.update`** - Обновление существующего объявления
   - *Бизнес-правило*: Только владелец может изменять объявление

3. **`ads.read`** - Получение информации об объявлении
   - *Бизнес-правило*: Для private-объявлений доступ только для владельца

4. **`ads.delete`** - Удаление объявления
   - *Бизнес-правило*: Возможность удаления только при отсутствии активных сделок

5. **`ads.search`** - Поиск объявлений
   - *Бизнес-логика*: Возвращает public-объявления с пагинацией
   - *Фильтры*: По типу сделки, категории товара, региону

**Специальные методы**
6. **`ads.offers`** - Поиск совместимых предложений
   - *Бизнес-логика*: Для объявления спроса ("demand") возвращает подходящие предложения ("proposal")
   - *Критерии совместимости*:
      - Совпадение категории товара
      - Соответствие техническим характеристикам (±5%)
      - Географическая близость (для тяжелого оборудования)
   - *Возвращает*: Список объявлений с оценкой релевантности (0-100%)

#### 3. Бизнес-правила
1. **Видимость объявлений**:
   - `public`: Видно всем пользователям
   - `private`: Только для внутреннего использования компанией
   - `partners`: Видно партнерам по экосистеме

2. **Валидация товарных категорий**:
   - Допустимые категории: "подшипники", "крепеж", "инструмент", "электроника"
   - Новые категории требуют модерации

3. **Ограничения для новых пользователей**:
   - Максимум 5 активных объявлений
   - Лимит 10 поисковых запросов в минуту

#### 4. Бизнес-метрики успеха
1. **Скорость подбора предложений**: 95% запросов должны возвращать результаты < 2 сек
2. **Точность соответствия**: Не менее 90% релевантных предложений для спроса
3. **Конверсия**: 30% просмотров предложений → контакты между компаниями
4. **Ограничения**:
   - Новые пользователи: 10 запросов/минуту
   - Верифицированные компании: 100 запросов/минуту
