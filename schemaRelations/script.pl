use strict;
use warnings;
use Data::Dumper;
use XML::Twig;
use JSON;

my $filename = $ARGV[0];
my $json_filename = $ARGV[1] . ".json";
my $prefix   = $ARGV[1] . ':';
my %elems;

my $twig = XML::Twig->new->parsefile($filename);

sub referenceScan {
    my ( $baseClass, $elems_ref ) = @_;

    my @checkedClasses;
    my @uncheckedClasses;

    push @uncheckedClasses, $baseClass;
    while (@uncheckedClasses) {
        my $parentClassName = pop @uncheckedClasses;

        for my $className ( keys %$elems_ref ) {
            for my $childClass ( @{ $elems_ref->{$className} } ) {

                if ( ( $childClass->{substitutionGroup} // '' ) eq
                    $parentClassName )
                {

                    # tylko jeśli rodzic istnieje w elems i ma referencesTo
                    if ( exists $elems_ref->{$parentClassName}
                        && @{ $elems_ref->{$parentClassName} } )
                    {
                        push @{ $childClass->{referencesTo} },
                          @{ $elems_ref->{$parentClassName}[0]{referencesTo} };
                    }

                    # dodajemy dziecko do kolejki, jeśli nie było sprawdzane
                    push @uncheckedClasses, $className
                      unless grep { $_ eq $className } @checkedClasses;
                }
            }
        }

        push @checkedClasses, $parentClassName;
    }

}

#Skan 1
for my $el ( $twig->findnodes('element') ) {
    my $name = $el->att('name');
    next unless defined $name;

    my $sub = $el->att('substitutionGroup') // '';
    $sub =~ s/^$prefix//;

    push @{ $elems{$name} ||= [] },
      {
        type              => $el->att('type') // '',
        substitutionGroup => $sub,
        referencesTo      => [],
      };
}

#Skan 2
for my $ct ( $twig->findnodes('complexType') ) {
    my $ct_name = $ct->att('name');
    next unless defined $ct_name;

    # Krok 1. Sprawdzamy atrybut `base` dla etykiety extension
    my $extension = $ct->first_descendant('extension');
    next unless defined $extension;
    my $extension_base = $extension->att('base');
    next unless defined $extension_base;

    while ( my ( $className, $classArrayRef ) = each %elems ) {
        foreach my $item (@$classArrayRef) {
            if ( $className !~ /PropertyType/ ) {
                $item->{type} =~ s/^$prefix// if defined $item->{type};

                if ( $item->{type} eq $ct_name )
                {    # mamy przypasowanie klasa - typ klasy

                    #Teraz wpisujemy elementy referencjonowane ktore znajdziemy
                    my ($sequence) = $extension->children('sequence');
                    next unless $sequence;

                    for my $element ( $sequence->children('element') ) {

                        # pobieramy atrybuty
                        my $name = $element->att('name') // '';
                        my $type = $element->att('type') // '';
                        if (   $type =~ /^\Q$prefix\E/
                            && $type =~ /PropertyType/ )
                        {
                            $name =~ s/^.*:// if $type;
                            push @{ $elems{$className}[0]{referencesTo} },
                              $name;
                        }

                    }
                }
            }
        }
    }
}

referenceScan( "gml:AbstractFeature", \%elems );

my $json = JSON->new->utf8->pretty->encode(\%elems);

open my $fh, '>', $json_filename or die $!;
print $fh $json;
close $fh;

exit;
