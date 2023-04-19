public class RedBlackTree {
    private Knot root;
    private Knot NULL;

    private void preorder(Knot knot) {
        if (knot != NULL) {
            System.out.print(knot.value + " ");
            preorder(knot.leftRotate);
            preorder(knot.rightRotate);
        }
    }

    private void inorder(Knot knot) {
        if (knot != NULL) {
            inorder(knot.leftRotate);
            System.out.print(knot.value + " ");
            inorder(knot.rightRotate);
        }
    }

    private void order(Knot knot) {
        if (knot != NULL) {
            order(knot.leftRotate);
            order(knot.rightRotate);
            System.out.print(knot.value + " ");
        }
    }

    private Knot treeSearch(Knot knot, int key) {
        if (knot == NULL || key == knot.value) {
            return knot;
        }

        if (key < knot.value) {
            return treeSearch(knot.leftRotate, key);
        }
        return treeSearch(knot.rightRotate, key);
    }

    private void editingAfterDeletion(Knot node_x) {
        Knot descendent;
        while (node_x != root && node_x.color == 0) {
            if (node_x == node_x.ancestor.leftRotate) {
                descendent = node_x.ancestor.rightRotate;
                if (descendent.color == 1) {
                    descendent.color = 0;
                    node_x.ancestor.color = 1;
                    leftRotate(node_x.ancestor);
                    descendent = node_x.ancestor.rightRotate;
                }

                if (descendent.leftRotate.color == 0 && descendent.rightRotate.color == 0) {
                    descendent.color = 1;
                    node_x = node_x.ancestor;
                } else {
                    if (descendent.rightRotate.color == 0) {
                        descendent.leftRotate.color = 0;
                        descendent.color = 1;
                        rightRotate(descendent);
                        descendent = node_x.ancestor.rightRotate;
                    }

                    descendent.color = node_x.ancestor.color;
                    node_x.ancestor.color = 0;
                    descendent.rightRotate.color = 0;
                    leftRotate(node_x.ancestor);
                    node_x = root;
                }
            } else {
                descendent = node_x.ancestor.leftRotate;
                if (descendent.color == 1) {
                    descendent.color = 0;
                    node_x.ancestor.color = 1;
                    rightRotate(node_x.ancestor);
                    descendent = node_x.ancestor.leftRotate;
                }

                if (descendent.rightRotate.color == 0 && descendent.rightRotate.color == 0) {
                    descendent.color = 1;
                    node_x = node_x.ancestor;
                } else {
                    if (descendent.leftRotate.color == 0) {
                        descendent.rightRotate.color = 0;
                        descendent.color = 1;
                        leftRotate(descendent);
                        descendent = node_x.ancestor.leftRotate;
                    }

                    descendent.color = node_x.ancestor.color;
                    node_x.ancestor.color = 0;
                    descendent.leftRotate.color = 0;
                    rightRotate(node_x.ancestor);
                    node_x = root;
                }
            }
        }
        node_x.color = 0;
    }

    private void add_knot(Knot node_a, Knot node_b) {
        if (node_a.ancestor == null) {
            root = node_b;
        } else if (node_a == node_a.ancestor.leftRotate) {
            node_a.ancestor.leftRotate = node_b;
        } else {
            node_a.ancestor.rightRotate = node_b;
        }
        node_b.ancestor = node_a.ancestor;
    }

    private void deleteKnot(Knot knot, int key) {
        Knot node_z = NULL;
        Knot node_x, node_y;
        while (knot != NULL) {
            if (knot.value == key) {
                node_z = knot;
            }

            if (knot.value <= key) {
                knot = knot.rightRotate;
            } else {
                knot = knot.leftRotate;
            }
        }

        if (node_z == NULL) {
            System.out.println();
            System.out.println("Отсутствует значение!!!");
            System.out.println();
            return;
        }

        node_y = node_z;
        int node_Y_color = node_y.color;
        if (node_z.leftRotate == NULL) {
            node_x = node_z.rightRotate;
            add_knot(node_z, node_z.rightRotate);
        } else if (node_z.rightRotate == NULL) {
            node_x = node_z.leftRotate;
            add_knot(node_z, node_z.leftRotate);
        } else {
            node_y = node_min(node_z.rightRotate);
            node_Y_color = node_y.color;
            node_x = node_y.rightRotate;
            if (node_y.ancestor == node_z) {
                node_x.ancestor = node_y;
            } else {
                add_knot(node_y, node_y.rightRotate);
                node_y.rightRotate = node_z.rightRotate;
                node_y.rightRotate.ancestor = node_y;
            }

            add_knot(node_z, node_y);
            node_y.leftRotate = node_z.leftRotate;
            node_y.leftRotate.ancestor = node_y;
            node_y.color = node_z.color;
        }
        if (node_Y_color == 0) {
            editingAfterDeletion(node_x);
        }
    }

    private void editingAfterAddNode(Knot node_c) {
        Knot node_d;
        while (node_c.ancestor.color == 1) {
            if (node_c.ancestor == node_c.ancestor.ancestor.rightRotate) {
                node_d = node_c.ancestor.ancestor.leftRotate;
                if (node_d.color == 1) {
                    node_d.color = 0;
                    node_c.ancestor.color = 0;
                    node_c.ancestor.ancestor.color = 1;
                    node_c = node_c.ancestor.ancestor;
                } else {
                    if (node_c == node_c.ancestor.leftRotate) {
                        node_c = node_c.ancestor;
                        rightRotate(node_c);
                    }
                    node_c.ancestor.color = 0;
                    node_c.ancestor.ancestor.color = 1;
                    leftRotate(node_c.ancestor.ancestor);
                }
            } else {
                node_d = node_c.ancestor.ancestor.rightRotate;

                if (node_d.color == 1) {
                    node_d.color = 0;
                    node_c.ancestor.color = 0;
                    node_c.ancestor.ancestor.color = 1;
                    node_c = node_c.ancestor.ancestor;
                } else {
                    if (node_c == node_c.ancestor.rightRotate) {
                        node_c = node_c.ancestor;
                        leftRotate(node_c);
                    }
                    node_c.ancestor.color = 0;
                    node_c.ancestor.ancestor.color = 1;
                    rightRotate(node_c.ancestor.ancestor);
                }
            }
            if (node_c == root) {
                break;
            }
        }
        root.color = 0;
    }

    private void print_console(Knot root, String indent, boolean last) {
        if (root != NULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("RIGHT ");
                indent += "   ";
            } else {
                System.out.print("LEFT ");
                indent += "  ";
            }

            String node_color = root.color == 1 ? "\u001B[31m" + " RED " + "\u001B[0m" : " BLACK ";
            System.out.println(root.value + " (" + node_color + ") ");
            print_console(root.leftRotate, indent, false);
            print_console(root.rightRotate, indent, true);
        }
    }

    public RedBlackTree() {
        NULL = new Knot();
        NULL.color = 0;
        NULL.leftRotate = null;
        NULL.rightRotate = null;
        root = NULL;
    }

    public void preorder() {
        preorder(this.root);
    }

    public void inorder() {
        inorder(this.root);
    }

    public void order() {
        order(this.root);
    }

    public Knot searchTree(int k) {
        return treeSearch(this.root, k);
    }

    public Knot node_min(Knot knot) {
        while (knot.leftRotate != NULL) {
            knot = knot.leftRotate;
        }
        return knot;
    }

    public Knot node_max(Knot knot) {
        while (knot.rightRotate != NULL) {
            knot = knot.rightRotate;
        }
        return knot;
    }

    public Knot heir(Knot heir_x) {
        if (heir_x.rightRotate != NULL) {
            return node_min(heir_x.rightRotate);
        }

        Knot heir_y = heir_x.ancestor;
        while (heir_y != NULL && heir_x == heir_y.rightRotate) {
            heir_x = heir_y;
            heir_y = heir_y.ancestor;
        }
        return heir_y;
    }

    public Knot precursor(Knot precursor_x) {
        if (precursor_x.leftRotate != NULL) {
            return node_max(precursor_x.leftRotate);
        }

        Knot precursor_y = precursor_x.ancestor;
        while (precursor_y != NULL && precursor_x == precursor_y.leftRotate) {
            precursor_x = precursor_y;
            precursor_y = precursor_y.ancestor;
        }

        return precursor_y;
    }

    public void leftRotate(Knot rotateL_x) {
        Knot rotateL_y = rotateL_x.rightRotate;
        rotateL_x.rightRotate = rotateL_y.leftRotate;
        if (rotateL_y.leftRotate != NULL) {
            rotateL_y.leftRotate.ancestor = rotateL_x;
        }
        rotateL_y.ancestor = rotateL_x.ancestor;
        if (rotateL_x.ancestor == null) {
            this.root = rotateL_y;
        } else if (rotateL_x == rotateL_x.ancestor.leftRotate) {
            rotateL_x.ancestor.leftRotate = rotateL_y;
        } else {
            rotateL_x.ancestor.rightRotate = rotateL_y;
        }
        rotateL_y.leftRotate = rotateL_x;
        rotateL_x.ancestor = rotateL_y;
    }

    public void rightRotate(Knot rotateR_x) {
        Knot rotateR_y = rotateR_x.leftRotate;
        rotateR_x.leftRotate = rotateR_y.rightRotate;
        if (rotateR_y.rightRotate != NULL) {
            rotateR_y.rightRotate.ancestor = rotateR_x;
        }
        rotateR_y.ancestor = rotateR_x.ancestor;
        if (rotateR_x.ancestor == null) {
            this.root = rotateR_y;
        } else if (rotateR_x == rotateR_x.ancestor.rightRotate) {
            rotateR_x.ancestor.rightRotate = rotateR_y;
        } else {
            rotateR_x.ancestor.leftRotate = rotateR_y;
        }
        rotateR_y.rightRotate = rotateR_x;
        rotateR_x.ancestor = rotateR_y;
    }

    public void addKnot(int key) {
        Knot knot = new Knot();
        knot.ancestor = null;
        knot.value = key;
        knot.leftRotate = NULL;
        knot.rightRotate = NULL;
        knot.color = 1;

        Knot node_y = null;
        Knot node_x = this.root;

        while (node_x != NULL) {
            node_y = node_x;
            if (knot.value < node_x.value) {
                node_x = node_x.leftRotate;
            } else {
                node_x = node_x.rightRotate;
            }
        }

        knot.ancestor = node_y;
        if (node_y == null) {
            root = knot;
        } else if (knot.value < node_y.value) {
            node_y.leftRotate = knot;
        } else {
            node_y.rightRotate = knot;
        }

        if (knot.ancestor == null) {
            knot.color = 0;
            return;
        }

        if (knot.ancestor.ancestor == null) {
            return;
        }

        editingAfterAddNode(knot);
    }

    public Knot getRoot() {
        return this.root;
    }

    public void deleteknot(int data) {
        deleteKnot(this.root, data);
    }

    public void printRedBlackTree() {
        System.out.println();
        print_console(this.root, "", true);
    }

    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        rbt.addKnot(46);
        rbt.addKnot(31);
        rbt.addKnot(56);
        rbt.addKnot(51);
        rbt.addKnot(66);
        rbt.addKnot(48);
        rbt.printRedBlackTree();

        System.out.println();
        System.out.println("После удаления узла");

        rbt.deleteknot(31);
        rbt.printRedBlackTree();
    }
}