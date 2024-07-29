# Bean

Created by: Khánh Linh Phạm
Created time: July 15, 2024 4:51 PM

# Khái niệm Bean

- Trong mô hình của Spring Framework, Bean là các đối tượng mà IoC Container quản lý. Chúng là những thành phần cốt lõi được sử dụng để xây dựng ứng dụng. Bất kỳ đối tượng nào được khởi tạo, lắp ráp và quản lý bởi Spring IoC Container đều được gọi là Bean. Container này xử lý việc tạo ra và quản lý các Bean, bao gồm cả vòng đời của chúng từ khởi tạo đến hủy bỏ.
- **Theo mặc định, các bean trong Spring đều là singleton, nghĩa là chỉ có một instance duy nhất được tạo và được chia sẻ trong toàn bộ ứng dụng.**

### **IoC Container và Dependency Injection**

- **IoC Container (Inversion of Control Container)**: Đây là cơ chế mà Spring sử dụng để kiểm soát quá trình tạo ra các đối tượng. Thay vì các đối tượng tự tạo các phụ thuộc (dependencies) của chúng, IoC Container tạo ra chúng và “tiêm (inject)” vào các đối tượng cần. Điều này làm giảm sự phụ thuộc giữa các thành phần liên quan, làm cho ứng dụng dễ quản lý và mở rộng hơn.
- **Dependency Injection (DI)**: Là một mẫu thiết kế (design pattern) mà trong đó đối tượng (hay client) không tạo ra các đối tượng mà nó phụ thuộc, mà các đối tượng đó được cung cấp từ bên ngoài. Spring hỗ trợ ba kiểu tiêm phụ thuộc:
    - Field-based Injection
    - Constructor-based Injection
    - Setter-based Injection

# **Tạo Bean như thế nào?**

Trong SpringBoot, việc tạo Bean có thể ví như việc đặt các món đồ trong nhà bạn. Mỗi món đồ (hay Bean) có một mục đích và cách sử dụng riêng. SpringBoot cung cấp nhiều cách để bạn “đặt món đồ này vào nhà”, hay nói cách khác, tạo Bean. Dưới đây là hai phương pháp phổ biến nhất để bạn có thể tạo Bean trong ứng dụng của mình:

## **Sử dụng các Annotation Đánh Dấu Lên Class**

Khi bạn muốn Spring tự động nhận biết và quản lý một Bean, bạn có thể sử dụng các annotation như `@Component`, `@Service`, `@Repository`, và `@Controller`. Mỗi annotation này có một ý nghĩa riêng, phù hợp với loại “món đồ” mà bạn muốn đặt trong “ngôi nhà” của mình.

- **@Component**: Đây là cách chung nhất để đánh dấu một Bean. Nó cho biết đây là một đối tượng của ứng dụng mà bạn muốn Spring quản lý.
- **@Service**: Dùng cho các lớp thực hiện xử lý logic nghiệp vụ.
- **@Repository**: Sử dụng cho các lớp làm việc trực tiếp với cơ sở dữ liệu.
- **@Controller**: Đặc biệt dành cho các lớp xử lý các yêu cầu HTTP, đóng vai trò như một cầu nối giữa người dùng và ứng dụng của bạn.

### **Sử dụng @Bean Đánh Dấu Lên Method**

- Phương pháp thứ hai là định nghĩa Bean trong một lớp Java với annotation `@Configuration`. Đây là cách tạo Bean một cách rõ ràng hơn, thường được sử dụng khi bạn cần cấu hình chi tiết hơn hoặc tạo Bean theo điều kiện đặc biệt.
- Trong lớp `@Configuration`, bạn sẽ định nghĩa các phương thức trả về đối tượng của Bean, và mỗi phương thức này được đánh dấu bằng `@Bean`. Điều này cho Spring biết rằng mỗi đối tượng trả về từ phương thức là một Bean và nên được quản lý bởi IoC Container.

![Untitled](Bean%20995aacbaaf434b0f90e4526bec778da1/Untitled.png)

- Trong ví dụ này, `bookService` là một Bean được tạo ra và quản lý bởi Spring. Khi ứng dụng của bạn chạy, Spring sẽ tìm trong các lớp `@Configuration` để tạo và cấu hình các Bean theo định nghĩa.
- Mỗi cách trên có ưu điểm riêng và tùy thuộc vào nhu cầu cụ thể của ứng dụng bạn đang xây dựng, bạn có thể lựa chọn cách thức phù hợp để “đặt món đồ” vào “ngôi nhà” của mình. Việc lựa chọn đúng cách không chỉ giúp ứng dụng của bạn chạy trơn tru mà còn dễ dàng bảo trì và mở rộng trong tương lai.

# **Sử dụng Bean như thế nào?**

## **Field-based Injection**

- Field-based injection là phương thức tiêm phụ thuộc vào trường của một lớp. Spring sẽ tự động điền các giá trị phù hợp vào các trường được đánh dấu bằng `@Autowired`. Đây là phương pháp đơn giản nhất nhưng không được khuyến khích vì nó làm giảm tính mô-đun và khó kiểm soát các phụ thuộc.

![Untitled](Bean%20995aacbaaf434b0f90e4526bec778da1/Untitled%201.png)

- Trong ví dụ trên, `ProductService` cần truy cập `ProductRepository`. Spring sẽ tự động tìm bean phù hợp và tiêm vào trường `productRepository`.

## **Constructor-based Injection**

- Constructor-based injection là phương thức tiêm phụ thuộc được khuyến khích nhất. Nó yêu cầu bạn cung cấp các phụ thuộc của lớp thông qua hàm tạo. Điều này giúp rõ ràng các phụ thuộc của lớp và lớp không thể được tạo nếu thiếu bất kỳ phụ thuộc nào.

![Untitled](Bean%20995aacbaaf434b0f90e4526bec778da1/Untitled%202.png)

- Ở đây, `OrderService` phụ thuộc vào `CustomerService`. Spring sẽ tạo ra `OrderService` chỉ khi `CustomerService` đã sẵn sàng và được tiêm vào qua constructor.

## **Setter-based Injection**

- Setter-based injection cho phép bạn tiêm phụ thuộc thông qua setter thay vì hàm tạo. Điều này có thể hữu ích khi bạn cần phụ thuộc không bắt buộc hoặc có thể thay đổi sau khi đối tượng đã được tạo.

![Untitled](Bean%20995aacbaaf434b0f90e4526bec778da1/Untitled%203.png)

- Trong ví dụ này, `InventoryService` nhận `ItemRepository` thông qua setter. Phương thức setter này được gọi sau khi `InventoryService` được tạo, cho phép cập nhật hoặc thay đổi `itemRepository` sau này.

# **Cấu hình kết nối cơ sở dữ liệu trong `application.properties` hoặc `application.yml`:**

**application.properties:**

```
propertiesSao chép mã
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

```