

   Napisz program, który bêdzie s³u¿y³ do zarz¹dzania przez dewelopera sieci¹ osiedli mieszkaniowych. Deweloper mo¿e posiadaæ wiele osiedli, a w ramach osiedli mo¿e istnieæ wiele bloków, w których istnieje dowolna (skoñczona) liczba mieszkañ do wynajmu. Ka¿de pomieszczenie mo¿e byæ zamieszkane przez dowoln¹ liczbê osób, o których informacje nale¿y przechowywaæ (obiekty klasy Person), jednak zawsze pierwsza osoba, która rozpoczyna najem mieszkania jest osob¹ odpowiedzialn¹ za op³aty dot. wynajmu.

   W ramach najmu mieszkania (tylko je¿eli osoba najmuje mieszkanie w ramach osiedla) jest mo¿liwoœæ dodatkowego najmu zamkniêtego miejsca parkingowego, w ramach którego mo¿na przechowywaæ pojazdy, ale równie¿ ró¿ne przedmioty.

   Ka¿de mieszkanie jak i miejsce parkingowe posiadaj¹ informacjê o swojej powierzchni u¿ytkowej jak i unikalny numer identyfikacyjny, dziêki któremu mo¿emy jednoznacznie zdefiniowaæ obiekt symbolizuj¹cy mieszkanie lub miejsce parkingowe.

   Najemca mo¿e dowolnie zameldowywaæ i wymeldowywaæ osoby z mieszkania, jak i równie¿, jeœli posiada miejsce parkingowe, to mo¿e wk³adaæ i wyjmowaæ przedmioty oraz pojazdy. Ka¿da osoba mo¿e mieæ wynajêtych wiele mieszkañ i miejsc parkingowych, o ile sumaryczna liczba wynajmowanych mieszkañ i miejsc parkingowych przez dan¹ osobê (najemcê) nie jest wiêksza ni¿ 5. Ka¿de pomieszczenie mo¿e mieæ tylko jednego najemcê w jednym czasie.

   Nale¿y zapewniæ, aby identyfikatory pomieszczeñ by³y unikalne oraz tworzone automatycznie podczas tworzenia obiektu typu Mieszkanie i MiejsceParkingowe. Rozmiar powierzchni u¿ytkowej w przypadku oby wspomnianych typów pomieszczeñ nale¿y dostarczaæ podczas tworzenia obiektu pomieszczenia.

Mo¿liwe s¹ dwa sposoby wskazania rozmiaru pomieszczenia:

• poprzez podanie objêtoœci w metrach szeœciennych
• poprzez podanie rozmiarów pomieszczenia w postaci d³ugoœci, szerokoœci i wysokoœci pomieszczenia (dla uproszczenia zak³adamy, ¿e pomieszczenie jest prostopad³oœcianem. Przy tym podejœciu powierzchnia u¿ytkowa pomieszczenia zostaje wyliczona podczas tworzenia obiektu na podstawie przekazanych wartoœci).

   Pomieszczenie dla okreœlonego najemcy posiada równie¿ datê rozpoczêcia najmu oraz datê zakoñczenia najmu. Jeœli data zakoñczenia najmu siê przedawni³a, to zostaje wystosowane pismo

(obiekt typu File), które zostaje zapisane do obiektu klasy Person definiuj¹cego konkretnego najemcê.

   W zadaniu równie¿ nale¿y zaimplementowaæ mechanizm up³ywaj¹cego czasu za poœrednictwem w¹tków. W¹tek powinien co 5 sekund przesuwaæ datê o 1 dzieñ do przodu, symuluj¹c up³yw czasu. Równolegle powinny byæ co 10 sekund sprawdzane kwestie wynajmu, czy wszystkie pomieszczenia nadal s¹ w trakcie najmu, czy mo¿e wynajem pomieszczenia ju¿ usta³.

   Jeœli najem zostanie odnowiony lub najem zostanie anulowany przez najemcê, pismo dot. zad³u¿enia zostaje usuniête z akt osoby.

   Jeœli najemca nie odnowi najmu w przeci¹gu 30 dni, nale¿y wyczyœciæ pomieszczenie, którego najem siê zakoñczy³o, a pismo pozostaje w aktach.

W przypadku mieszkania przeprowadzamy eksmisjê osób zamieszka³ych w tym mieszkaniu.

   W przypadku miejsca parkingowego, w pierwszej kolejnoœci sprawdzamy czy znajduje siê na miejscu parkingowym pojazd. Jeœli tak, to w zwi¹zku z przyœpieszon¹ decyzj¹ komornicz¹ zostaje on sprzedany (z punktu widzenia programu usuwamy obiekt z pomieszczenia), a koszt sprzeda¿y pokrywa najem pomieszczenia na najbli¿sze 2 miesi¹ce. Jeœli nie by³o pojazdu, to utylizacji podlegaj¹ wszystkie przechowywane rzeczy na miejscu parkingowym.

   Osoba posiada ponad wymienione wczeœniej informacje takie dane jak imiê, nazwisko, pesel, adres, datê urodzenia.

   Jeœli najem bêdzie chcia³a rozpocz¹æ osoba z wiêcej ni¿ trzema zad³u¿eniami (co najmniej 3 obiekty typu File) na przestrzeni najmów na tle ca³ego osiedla, rzucony powinien zostaæ wyj¹tek ProblematicTenantException z komunikatem – „Osoba X posiada³a ju¿ najem pomieszczeñ: lista_pomieszczen”, gdzie X to imiê i nazwisko danej osoby, zaœ lista_pomieszczen definiuje wynajmowane pomieszczenia, które zosta³y wynajête.

   Ka¿dy z przedmiotów i pojazdów posiadaj¹ zapisan¹ informacjê o nazwie oraz polu powierzchni jak¹ zajmuje. Powierzchnia zajmowana przez przedmiot/pojazd mo¿e byæ dostarczona na dwa sposoby tak jak w przypadku pomieszczenia.

Pojazdy s¹ podzielone na rozró¿niaj¹ce je typy:

• samochód terenowy,
• samochód miejski,
• ³ódŸ,
• motocykl,
• amfibia

   Ka¿dy z pojazdów poza nazw¹ oraz powierzchni¹ musi posiadaæ cechy charakterystyczne dla danego typu pojazdu (np. pojemnoœæ silnika, typ pojazdu, typ silnika itp.). Dla ka¿dego typu pojazdu mog¹ powtarzaæ siê cechy charakterystyczne, jednak co najmniej jedna powinna byæ ró¿na na tle innych pojazdów.

   W przypadku wk³adania ka¿dego przedmiotu lub pojazdu do pomieszczenia musimy siê upewniæ, ¿e pomieszczenie jest w stanie pomieœciæ ten obiekt. Jeœli tak nie jest, zostaje rzucony wyj¹tek TooManyThingsException z komunikatem „Remove some old items to insert a new item”.

   Stan osób zamieszkuj¹cych osiedle wraz ze wszelkimi danymi dot. osoby, wynajmowanych pomieszczeñ, przedmiotów, itp. musi byæ zapisywane po wybraniu z menu odpowiedniej funkcjonalnoœci. Informacje zapisane w pliku powinny byæ zapisane przejrzyœcie i czytelnie dla cz³owieka z zachowaniem poni¿szych regu³:

• Pomieszczenia powinny byæ posortowane rosn¹co wed³ug rozmiaru pomieszczenia.
• Zawartoœæ pomieszczenia powinna byæ posortowana malej¹co wed³ug rozmiaru przedmiotu jeœli jest taki sam to wed³ug nazwy.

Zasada dzia³ania programu:

• W metodzie main nale¿y utworzyæ osiedle wraz z co najmniej dziesiêcioma gotowymi pomieszczeniami ró¿nego typu i rozmiaru oraz kilka (minimum 5) gotowych osób. Ze wstêpnie przydzielonymi najmami oraz umiejscowionymi przedmiotami na miejscach parkingowych.
• Po uruchomieniu programu, u¿ytkownik powinien mieæ mo¿liwoœæ za poœrednictwem konsoli poleceñ wywo³ania wszystkich wspomnianych funkcjonalnoœci. S¹ to m.in:

– zakoñczenia programu w dowolnym momencie
– wybrania któr¹ jest osob¹ (nie jest potrzebne ¿adne logowanie, wystarczy wskazanie np. unikalnego numeru osoby)
– wypisania swoich danych ³¹cznie z wynajêtymi pomieszczeniami
– wyœwietlenia wolnych pomieszczeñ
– wynajêcia nowego pomieszczenia, po uprzednim jego wybraniu
– wybrania pomieszczenia które wynajmuje dana osoba oraz wyœwietlenia zawartoœci pomieszczenia
– w³o¿enia nowych pojazdów/przedmiotów pamiêtaj¹c, aby nie przepe³niæ pomieszczenia
– wyjêcia przedmiotów lub pojazdów.
– wykonania polecenia zapisuj¹cego aktualny stan osiedla do pliku





